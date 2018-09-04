package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * UserExtSource entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class UserExtSource extends PerunEntity {

	public UserExtSource(Map<String,PerunAttribute> attributes) {
		super(PerunEntityType.USER_EXT_SOURCE, attributes);
	}

}