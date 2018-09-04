package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * Group entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Group extends PerunEntity {

	public Group(Map<String,PerunAttribute> attributes) {
		super(PerunEntityType.GROUP, attributes);
	}

}
