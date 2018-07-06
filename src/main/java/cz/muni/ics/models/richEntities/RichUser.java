package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.BooleanAttribute;
import cz.muni.ics.models.attributes.IntegerAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.attributes.StringAttribute;
import cz.muni.ics.models.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichUser extends User implements RichPerunEntity {

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

        if (keys.contains("firstName")) {
            res.add(new StringAttribute("firstName", super.getFirstName()));
        }

        if (keys.contains("middleName")) {
            res.add(new StringAttribute("middleName", super.getMiddleName()));
        }

        if (keys.contains("lastName")) {
            res.add(new StringAttribute("lastName", super.getLastName()));
        }

        if (keys.contains("titleBefore")) {
            res.add(new StringAttribute("titleBefore", super.getTitleBefore()));
        }

        if (keys.contains("titleAfter")) {
            res.add(new StringAttribute("titleAfter", super.getTitleAfter()));
        }

        if (keys.contains("serviceAcc")) {
            res.add(new BooleanAttribute("serviceAcc", super.isServiceAcc().toString()));
        }

        if (keys.contains("sponsoredAcc")) {
            res.add(new BooleanAttribute("sponsoredAcc", super.isSponsoredAcc().toString()));
        }

        for (PerunAttribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }

        return res;
    }

}
