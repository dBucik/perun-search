package cz.muni.ics;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Query;
import cz.muni.ics.models.Vo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //TODO add service as entity
    //TODO entityless attributes
    //TODO relationship attributes
    //TODO check names of attributes
    //TODO create parser for better input specification


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Query query = (Query) context.getBean("query");


        System.out.println("VO WITH Id: 1");
        System.out.println(query.getVo(1L));

        List<Vo> vos = query.getAllVos();
        System.out.println("");
        System.out.println("ALL VOS");
        for (Vo voItem: vos) {
            System.out.println(voItem);
        }


    }
}
