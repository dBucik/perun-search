package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.IntegerAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.attributes.StringAttribute;
import cz.muni.ics.models.entities.Facility;

import java.util.ArrayList;
import java.util.List;

/**
 * Facility entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichFacility extends Facility implements RichPerunEntity {

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

        if (keys.contains("name")) {
            res.add(new StringAttribute("name", super.getName()));
        }

        if (keys.contains("description")) {
            res.add(new StringAttribute("description", super.getDescription()));
        }

        for (PerunAttribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
