package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * Host entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Host extends PerunEntity {

    public Host(Map<String,PerunAttribute> attributes) {
        super(PerunEntityType.HOST, attributes);
    }

}
