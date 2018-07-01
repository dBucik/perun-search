package cz.muni.ics.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Service entity from Perun.
 * Core attributes are stored in entity and have their get methods.
 * Additional attributes are stored in list accessed with getAttributes method.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Service {

    private Long id;
    private String name;
    private Long ownerId;

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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

        if (keys.contains("ownerId")) {
            res.add(new Attribute("ownerId", ownerId.toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }
}
