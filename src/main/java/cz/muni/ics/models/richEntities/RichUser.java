package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * User entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichUser extends User {

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

        if (keys.contains("firstName")) {
            res.add(new Attribute("firstName", super.getFirstName()));
        }

        if (keys.contains("middleName")) {
            res.add(new Attribute("middleName", super.getMiddleName()));
        }

        if (keys.contains("lastName")) {
            res.add(new Attribute("lastName", super.getLastName()));
        }

        if (keys.contains("titleBefore")) {
            res.add(new Attribute("titleBefore", super.getTitleBefore()));
        }

        if (keys.contains("titleAfter")) {
            res.add(new Attribute("titleAfter", super.getTitleAfter()));
        }

        if (keys.contains("serviceAcc")) {
            res.add(new Attribute("serviceAcc", super.isServiceAcc().toString()));
        }

        if (keys.contains("sponsoredAcc")) {
            res.add(new Attribute("sponsoredAcc", super.isSponsoredAcc().toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }

        return res;
    }

}
