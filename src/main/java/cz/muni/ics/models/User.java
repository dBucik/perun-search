package cz.muni.ics.models;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class User {

    //core
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String titleBefore;
    private String titleAfter;
    private Long serviceAcc;
    private Long sponsoredAcc;

    private JSONPObject attributes;
}
