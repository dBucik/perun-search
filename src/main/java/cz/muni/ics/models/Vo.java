package cz.muni.ics.models;

import java.util.List;

public class Vo {

    //core
    private Long id;
    private String name;
    private String shortName;

    private List<Attribute> attributes;

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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
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