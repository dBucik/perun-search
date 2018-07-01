package cz.muni.ics;

import cz.muni.ics.JDBCTemplates.VoJdbcTemplate;
import cz.muni.ics.models.Vo;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    //TODO add service as entity
    //TODO entityless attributes
    //TODO relationship attributes
    //TODO check names of attributes
    //TODO create parser for better input specification


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        VoJdbcTemplate voJdbcTemplate = (VoJdbcTemplate) context.getBean("voJdbcTemplate");

        System.out.println("VO WITH Id: 1");
        Vo vo = voJdbcTemplate.getVo(1L);
        System.out.println(vo);

        List<Vo> vos = voJdbcTemplate.getVos();
        System.out.println("");
        System.out.println("ALL VOS");
        for (Vo voItem: vos) {
            System.out.println(voItem);
        }


    }
}
