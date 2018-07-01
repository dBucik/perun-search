package cz.muni.ics.models;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class UserExtSource {

    private Long id;
    private String loginExt;
    private Long extSourceId;
    private Short loa;

    private JSONPObject attributes;
}
