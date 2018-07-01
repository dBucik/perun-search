package cz.muni.ics.models;

import java.util.ArrayList;
import java.util.List;

public class Group {

    //core
    private Long id;
    private String name;
    private String description;
    private Long voId;
    private Long parentGroupId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVoId() {
        return voId;
    }

    public void setVoId(Long voId) {
        this.voId = voId;
    }

    public Long getParentGroupId() {
        return parentGroupId;
    }

    public void setParentGroupId(Long parentGroupId) {
        this.parentGroupId = parentGroupId;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributesByKeys(List<String> keys) {
        List<Attribute> res = new ArrayList<>();

        if (keys.contains("id")) {
            res.add(new Attribute("id", id.toString()));
        }

        if (keys.contains("name")) {
            res.add(new Attribute("name", name));
        }

        if (keys.contains("description")) {
            res.add(new Attribute("description", description));
        }

        if (keys.contains("voId")) {
            res.add(new Attribute("voId", voId.toString()));
        }

        if (keys.contains("parentGroupId")) {
            res.add(new Attribute("parentGroupId", parentGroupId.toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }
}
