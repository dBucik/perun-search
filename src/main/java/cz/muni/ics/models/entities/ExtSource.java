package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

/**
 * ExtSource entity from Perun.
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class ExtSource extends PerunEntity {

	public ExtSource(Map<String, PerunAttribute> attributes) {
		super(PerunEntityType.EXT_SOURCE, attributes);
	}
}
