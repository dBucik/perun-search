package cz.muni.ics.models.richEntities;


import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Group entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichGroup extends Group {

    private List<Attribute> attributes;

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributesByKeys(List<String> keys) {
        List<Attribute> res = new ArrayList<>();

        if (keys.contains("id")) {
            res.add(new Attribute("id", super.getId().toString()));
        }

        if (keys.contains("name")) {
            res.add(new Attribute("name", super.getName()));
        }

        if (keys.contains("description")) {
            res.add(new Attribute("description", super.getDescription()));
        }

        if (keys.contains("voId")) {
            res.add(new Attribute("voId", super.getVoId().toString()));
        }

        if (keys.contains("parentGroupId")) {
            res.add(new Attribute("parentGroupId", super.getParentGroupId().toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
