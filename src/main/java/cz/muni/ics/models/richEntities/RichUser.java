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

}
