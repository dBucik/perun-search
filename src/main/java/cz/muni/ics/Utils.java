package cz.muni.ics;

import cz.muni.ics.JdbcTemplates.UserJdbcTemplate;
import cz.muni.ics.JdbcTemplates.VoJdbcTemplate;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    //TODO entityless attributes
    //TODO relationship attributes
    //TODO write tests
    //TODO check names of attributes
    //TODO create parser for better input specification


    public static void main(String[] args) throws DatabaseIntegrityException {
        // TESTING QUERIES
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Query query = (Query) context.getBean("query");
        UserJdbcTemplate jdbcTemplate = (UserJdbcTemplate) context.getBean("userJdbcTemplate");

        List<User> users = jdbcTemplate.getUsersByName(null, null, null, "holub", null);
        System.out.println(users);
    }

    public static List<Attribute> convertAttrsFromInput(List<InputAttribute> input) {
        List<Attribute> result = new ArrayList<>();
        for (InputAttribute inputAttribute: input) {
            Attribute filter = new Attribute(inputAttribute.getKey(), inputAttribute.getValue());
            result.add(filter);
        }

        if (result.isEmpty()) {
            return null;
        }

        return result;
    }

}
