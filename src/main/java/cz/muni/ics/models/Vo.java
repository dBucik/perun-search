package cz.muni.ics.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Vo entity from Perun.
 * Core attributes are stored in entity and have their get methods.
 * Additional attributes are stored in list accessed with getAttributes method.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
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

    public List<Attribute> getAttributesByKeys(List<String> keys) {
        List<Attribute> res = new ArrayList<>();
        if (keys.contains("id")) {
            res.add(new Attribute("id", id.toString()));
        }

        if (keys.contains("name")) {
            res.add(new Attribute("name", name));
        }

        if (keys.contains("shortName")) {
            res.add(new Attribute("shortName", shortName));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Id: " + id
                + ", name: " + name
                + ", shortName: " + shortName
                + ", attributes: " + attributes;
    }
}