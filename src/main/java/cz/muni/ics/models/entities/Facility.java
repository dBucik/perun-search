package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * Facility entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Facility extends PerunEntity {

	public Facility(Map<String,PerunAttribute> attributes) {
		super(PerunEntityType.FACILITY, attributes);
	}

}
