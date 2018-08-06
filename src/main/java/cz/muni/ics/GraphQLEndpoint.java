package cz.muni.ics;

import com.coxautodev.graphql.tools.SchemaParser;
import cz.muni.ics.models.Query;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.servlet.AbstractGraphQLHttpServlet;
import graphql.servlet.GraphQLInvocationInputFactory;
import graphql.servlet.GraphQLObjectMapper;
import graphql.servlet.GraphQLQueryInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends AbstractGraphQLHttpServlet {

	private GraphQLInvocationInputFactory invocationInputFactory;
	private GraphQLQueryInvoker queryInvoker;
	private GraphQLObjectMapper objectMapper;

	@Override
	protected GraphQLQueryInvoker getQueryInvoker() {
		return queryInvoker;
	}

	@Override
	protected GraphQLInvocationInputFactory getInvocationInputFactory() {
		return invocationInputFactory;
	}

	@Override
	protected GraphQLObjectMapper getGraphQLObjectMapper() {
		return objectMapper;
	}

	public GraphQLEndpoint() {
		invocationInputFactory = GraphQLInvocationInputFactory.newBuilder(buildSchema()).build();
		queryInvoker = GraphQLQueryInvoker.newBuilder().build();
		objectMapper = GraphQLObjectMapper.newBuilder().build();
	}

	private static GraphQLSchema buildSchema() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Query query = (Query) context.getBean("query");
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(query)
                .build()
                .makeExecutableSchema();
    }

}
