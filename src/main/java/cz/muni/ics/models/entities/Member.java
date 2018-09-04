package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * Member entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Member extends PerunEntity {

    public Member(Map<String,PerunAttribute> attributes) {
        super(PerunEntityType.MEMBER, attributes);
    }

}
