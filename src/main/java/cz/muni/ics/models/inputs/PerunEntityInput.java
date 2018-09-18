package cz.muni.ics.models.inputs;

import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.models.attributes.InputAttribute;

import java.util.ArrayList;
import java.util.List;

public abstract class PerunEntityInput {

	public final String ENTITY_ID_FIELD;
	public final String ENTITY_TABLE;
	public final String ATTRIBUTES_TABLE;
	public final String ATTRIBUTES_NAMES_TABLE;
	public final boolean isTopLevel;

	private List<InputAttribute> core = new ArrayList<>();
	private List<InputAttribute> attributes = new ArrayList<>();
	private List<String> attributesNames = new ArrayList<>();
	private List<PerunEntityInput> entities = new ArrayList<>();

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

	public List<PerunEntityInput> getEntities() {
		return entities;
	}

	public void setEntities(List<PerunEntityInput> entities) {
		this.entities = entities;
	}

	public JDBCQuery toQuery() {

	}

	public void addAttribute(InputAttribute attribute) {
		this.attributes.add(attribute);
	}

	public void addAttributeName(String attributeName) {
		this.attributesNames.add(attributeName);
	}

}
