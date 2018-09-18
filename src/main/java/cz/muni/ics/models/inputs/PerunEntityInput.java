package cz.muni.ics.models.inputs;

import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.models.attributes.InputAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PerunEntityInput {

	public final String ENTITY_ID_FIELD;
	public final String ENTITY_TABLE;
	public final String ATTRIBUTES_TABLE;
	public final String ATTRIBUTES_NAMES_TABLE;
	public final boolean isTopLevel;

	private List<InputAttribute> core = new ArrayList<>();
	private List<InputAttribute> attributes = new ArrayList<>();
	private List<String> attributesNames = new ArrayList<>();
	private Map<String, PerunEntityInput> entities = new HashMap<>();

	public PerunEntityInput(String entityIdField, String entityTable, String attributesTable,
							String attributesNamesTable, boolean isTopLevel) {
		this.ENTITY_ID_FIELD = entityIdField;
		this.ENTITY_TABLE = entityTable;
		this.ATTRIBUTES_TABLE = attributesTable;
		this.ATTRIBUTES_NAMES_TABLE = attributesNamesTable;
		this.isTopLevel = isTopLevel;
	}

	public List<InputAttribute> getCore() {
		return core;
	}

	public void setCore(List<InputAttribute> core) {
		this.core = core;
	}

	public List<InputAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<InputAttribute> attributes) {
		this.attributes = attributes;
	}

	public List<String> getAttributesNames() {
		return attributesNames;
	}

	public void setAttributesNames(List<String> attributesNames) {
		this.attributesNames = attributesNames;
	}

	public Map<String, PerunEntityInput> getEntities() {
		return entities;
	}

	public void setEntities(Map<String, PerunEntityInput> entities) {
		this.entities = entities;
	}

	public abstract JDBCQuery toQuery();

	public void addAttribute(InputAttribute attribute) {
		this.attributes.add(attribute);
	}

	public void addAttributeName(String attributeName) {
		this.attributesNames.add(attributeName);
	}

	public abstract String getName();
}
