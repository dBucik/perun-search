package cz.muni.ics;

import cz.muni.ics.models.Query;
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
import graphql.schema.idl.TypeRuntimeWiring;
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
	private Query query;

	@Autowired
	ResourceLoader resourceLoader;

	@RequestMapping("/graphql")
	public String process(@RequestParam(value = "query") String queryString) {
		queryString = "{getGroups(" +
				"attributes: [{key: \"id\", type: \"INTEGER\", value: \"1\"}])" +
				"{ attributes " +
					"{ value }" +
				"}} ";
		ExecutionInput.Builder executionInput = newExecutionInput()
				.query(queryString);

		AppWiring.Context context = new AppWiring.Context(query);
		executionInput.context(context);

		GraphQLSchema schema = buildSchema();
		DataLoaderRegistry dataLoaderRegistry = context.getDataLoaderRegistry();


		DataLoaderDispatcherInstrumentation dlInstrumentation =
				new DataLoaderDispatcherInstrumentation(dataLoaderRegistry, newOptions().includeStatistics(true));

		Instrumentation instrumentation = new ChainedInstrumentation(
				Arrays.asList(new TracingInstrumentation(), dlInstrumentation)
		);

		GraphQL graphQL = GraphQL
				.newGraphQL(schema)
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

			RuntimeWiring wiring = RuntimeWiring.newRuntimeWiring()
					.type(typeRuntimeWiring()
					).build();

			schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		}
		return schema;
	}

	private TypeRuntimeWiring typeRuntimeWiring() {
		return newTypeWiring("Query")
				.dataFetcher("getExtSources", AppWiring.getEntitiesFetcher())
				.dataFetcher("getFacilities", AppWiring.getEntitiesFetcher())
				.dataFetcher("getGroups", AppWiring.getEntitiesFetcher())
				.dataFetcher("getHosts", AppWiring.getEntitiesFetcher())
				.dataFetcher("getMembers", AppWiring.getEntitiesFetcher())
				.dataFetcher("getOwners", AppWiring.getEntitiesFetcher())
				.dataFetcher("getResources", AppWiring.getEntitiesFetcher())
				.dataFetcher("getServices", AppWiring.getEntitiesFetcher())
				.dataFetcher("getUsers", AppWiring.getEntitiesFetcher())
				.dataFetcher("getUserExtSources", AppWiring.getEntitiesFetcher())
				.dataFetcher("getVos", AppWiring.getEntitiesFetcher())
				.build();
	}

	private Reader loadSchemaFile() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:schema.graphqls");
		return new InputStreamReader(resource.getInputStream());
	}

}
