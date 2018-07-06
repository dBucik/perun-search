package cz.muni.ics.models.richEntities;

import cz.muni.ics.models.attributes.IntegerAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.attributes.StringAttribute;
import cz.muni.ics.models.entities.UserExtSource;

import java.util.ArrayList;
import java.util.List;

/**
 * UserExtSource entity from Perun with additional attributes.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class RichUserExtSource extends UserExtSource implements RichPerunEntity {

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

        if (keys.contains("loginExt")) {
            res.add(new StringAttribute("loginExt", super.getLoginExt()));
        }

        if (keys.contains("extSourcesId")) {
            res.add(new IntegerAttribute("extSourcesId", super.getExtSourcesId().toString()));
        }

        if (keys.contains("loa")) {
            res.add(new StringAttribute("loa", super.getLoa().toString()));
        }

        for (PerunAttribute attr: attributes) {
            if (keys.contains(attr.getKey())) {
                res.add(attr);
            }
        }
        return res;
    }

}
