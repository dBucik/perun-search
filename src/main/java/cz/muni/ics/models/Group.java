package cz.muni.ics.models;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class Group {

    //core
    private Long id;
    private String name;
    private String description;
    private Long voId;
    private Long parentGroupId;

    private JSONPObject attributes;

}
