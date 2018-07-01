package cz.muni.ics;

import cz.muni.ics.JDBCTemplates.VoJdbcTemplate;
import cz.muni.ics.models.Vo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    //TODO entityless attributes
    //TODO relationship attributes
    //TODO check names of attributes
    //TODO getters and setters for entities
    //TODO connect to RPC
    //TODO write queries for RPC
    //TODO write queries for DB
    //TODO create parser for better input specification


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        VoJdbcTemplate voJdbcTemplate = (VoJdbcTemplate) context.getBean("voJDBCTemplate");

        for (Vo vo : voJdbcTemplate.getVos()) {
            System.out.println(vo);
        }
    }
}
