package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * Resource entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class Resource extends PerunEntity {

	public Resource(Map<String,PerunAttribute> attributes) {
		super(PerunEntityType.RESOURCE, attributes);
	}

}