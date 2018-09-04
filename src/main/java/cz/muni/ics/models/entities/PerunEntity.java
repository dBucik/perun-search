package cz.muni.ics.models.entities;

import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Basic perun entity. Specific entities extend this class
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
public class PerunEntity {

	private PerunEntityType type;
	private Map<String, PerunAttribute> attributes;

	public PerunEntity(PerunEntityType type, Map<String, PerunAttribute> attributes) {
		this.type = type;
		this.attributes = attributes;
	}

	public Map<String, PerunAttribute> getAttributesMap() {
		return attributes;
	}

	public void setAttributes(Map<String, PerunAttribute> attributes) {
		this.attributes = attributes;
	}

	public PerunAttribute getAttributeByKey(String key) {
		return attributes.get(key);
	}

	public List<PerunAttribute> getAttributes() {
		return new ArrayList<>(attributes.values());
	}

    @Override
    public String toString() {
        return "PerunEntity: " + type + " [" +
				"attributes: "+ attributes +
                "]";
    }
}