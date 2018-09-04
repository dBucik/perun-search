package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * Vo entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Vo extends PerunEntity {

    public Vo(Map<String,PerunAttribute> attributes) {
        super(PerunEntityType.VO, attributes);
    }

}