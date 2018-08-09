package cz.muni.ics;

import cz.muni.ics.DAOs.VoDAO;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.execution.instrumentation.ChainedInstrumentation;
import graphql.execution.instrumentation.Instrumentation;
import graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentation;
import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

import static graphql.ExecutionInput.newExecutionInput;
import static graphql.execution.instrumentation.dataloader.DataLoaderDispatcherInstrumentationOptions.newOptions;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@RestController
public class MainController {

	private GraphQLSchema schema;

	@Autowired
	VoDAO voDAO;

	@Autowired
	ResourceLoader resourceLoader;

	@RequestMapping("/graphql")
	public String process(@RequestParam(value = "query") String query) {
		ExecutionInput.Builder executionInput = newExecutionInput()
				.query(query);

		AppWiring.Context context = new AppWiring.Context(voDAO);
		executionInput.context(context);

		GraphQLSchema schema = buildSchema();
		DataLoaderRegistry dataLoaderRegistry = context.getDataLoaderRegistry();


		DataLoaderDispatcherInstrumentation dlInstrumentation =
				new DataLoaderDispatcherInstrumentation(dataLoaderRegistry, newOptions().includeStatistics(true));

		Instrumentation instrumentation = new ChainedInstrumentation(
				Arrays.asList(new TracingInstrumentation(), dlInstrumentation)
		);

		// finally you build a runtime graphql object and execute the query
		GraphQL graphQL = GraphQL
				.newGraphQL(schema)
				// instrumentation is pluggable
				.instrumentation(instrumentation)
				.build();
		ExecutionResult executionResult = graphQL.execute(executionInput.build());

		if (executionResult.getErrors() != null && executionResult.getErrors().size() > 0) {
			return executionResult.getErrors().toString();
		}

		return executionResult.getData().toString();
	}

	private GraphQLSchema buildSchema() {
		if (schema == null) {
			TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
			try (Reader streamReader = loadSchemaFile()) {
				typeRegistry = new SchemaParser().parse(streamReader);
			} catch (IOException e) {
				//TODO
			}

			RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring().build();
			//TODO write wiring

			schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		}
		return schema;
	}

	private Reader loadSchemaFile() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:schema.graphqls");
		return new InputStreamReader(resource.getInputStream());
	}

}
