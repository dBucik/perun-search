package cz.muni.ics;

import com.coxautodev.graphql.tools.SchemaParser;
import cz.muni.ics.models.Query;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.annotation.WebServlet;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super (buildSchema());
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

    @Override
    protected List<GraphQLError> filterGraphQLErrors(List<GraphQLError> errors) {
        return errors.stream()
                .filter(e -> e instanceof ExceptionWhileDataFetching || super.isClientError(e))
                .map(e -> e instanceof ExceptionWhileDataFetching ? new SanitizedError((ExceptionWhileDataFetching) e) : e)
                .collect(Collectors.toList());
    }
}
