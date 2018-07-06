package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.BooleanAttribute;
import cz.muni.ics.models.attributes.IntegerAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.attributes.StringAttribute;
import cz.muni.ics.models.entities.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Member entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichMember extends Member implements RichPerunEntity {

    private List<PerunAttribute> attributes;

    @Override
    public List<PerunAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(List<PerunAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<PerunAttribute> getAttributesByKeys(List<String> keys) {
        List<PerunAttribute> res = new ArrayList<>();

        if (keys.contains("id")) {
            res.add(new IntegerAttribute("id", super.getId().toString()));
        }

        if (keys.contains("userId")) {
            res.add(new IntegerAttribute("userId", super.getUserId().toString()));
        }

        if (keys.contains("voId")) {
            res.add(new IntegerAttribute("voId", super.getVoId().toString()));
        }

        if (keys.contains("status")) {
            res.add(new StringAttribute("status", super.getStatus()));
        }

        if (keys.contains("sponsored")) {
            res.add(new BooleanAttribute("sponsored", super.getSponsored().toString()));
        }

        for (PerunAttribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
