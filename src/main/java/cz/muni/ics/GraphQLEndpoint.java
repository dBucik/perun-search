package cz.muni.ics;

import com.coxautodev.graphql.tools.SchemaParser;
import cz.muni.ics.JDBCTemplates.VoJdbcTemplate;
import cz.muni.ics.models.Query;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super (buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        VoJdbcTemplate voJdbcTemplate = (VoJdbcTemplate) context.getBean("voJDBCTemplate");
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(voJdbcTemplate))
                .build()
                .makeExecutableSchema();
    }
}
