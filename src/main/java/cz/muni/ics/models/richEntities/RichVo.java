package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Vo entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichVo extends Vo {

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

        if (keys.contains("shortName")) {
            res.add(new Attribute("shortName", super.getShortName()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
