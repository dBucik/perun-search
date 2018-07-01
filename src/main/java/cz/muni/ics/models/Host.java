package cz.muni.ics.models;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class Host {

    //core
    private Long id;
    private String description;
    private String hostname;
    private Long facilityId;

    private JSONPObject attributes;
}
