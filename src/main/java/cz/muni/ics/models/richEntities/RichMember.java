package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Member entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichMember extends Member {

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

        if (keys.contains("userId")) {
            res.add(new Attribute("userId", super.getUserId().toString()));
        }

        if (keys.contains("voId")) {
            res.add(new Attribute("voId", super.getVoId().toString()));
        }

        if (keys.contains("status")) {
            res.add(new Attribute("status", super.getStatus()));
        }

        if (keys.contains("sponsored")) {
            res.add(new Attribute("sponsored", super.getSponsored().toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
