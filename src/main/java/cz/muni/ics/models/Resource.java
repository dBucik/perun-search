package cz.muni.ics.models;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class Resource {

    //core
    private Long id;
    private Long facilityId;
    private String name;
    private String description;
    private Long voId;

    private JSONPObject attributes;
}
