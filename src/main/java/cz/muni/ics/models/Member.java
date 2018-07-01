package cz.muni.ics.models;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class Member {

    //core
    private Long id;
    private Long userId;
    private Long voId;
    private String status;
    private Boolean sponsored;

    private JSONPObject attributes;

}
