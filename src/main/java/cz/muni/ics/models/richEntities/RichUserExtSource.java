package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.entities.UserExtSource;

import java.util.ArrayList;
import java.util.List;

/**
 * UserExtSource entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichUserExtSource extends UserExtSource {

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

        if (keys.contains("loginExt")) {
            res.add(new Attribute("loginExt", super.getLoginExt()));
        }

        if (keys.contains("extSourcesId")) {
            res.add(new Attribute("extSourcesId", super.getExtSourcesId().toString()));
        }

        if (keys.contains("loa")) {
            res.add(new Attribute("loa", super.getLoa().toString()));
        }

        for (Attribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
