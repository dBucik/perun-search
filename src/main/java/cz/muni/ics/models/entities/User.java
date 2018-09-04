package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * User entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class User extends PerunEntity {

    public User(Map<String,PerunAttribute> attributes) {
        super(PerunEntityType.USER, attributes);
    }

}
