package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.Map;

public class Owner extends PerunEntity {

	public Owner(Map<String,PerunAttribute> attributes) {
		super(PerunEntityType.OWNER, attributes);
	}

}
