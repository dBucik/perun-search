package cz.muni.ics.models;

import org.json.JSONObject;

public class Vo {

    //core
    private Long id;
    private String name;
    private String shortName;

    private JSONObject attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Id: " + id
                + ", name: " + name
                + ", shortName: " + shortName
                + ", attributes: " + attributes;
    }
}