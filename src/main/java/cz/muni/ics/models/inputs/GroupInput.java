package cz.muni.ics.models.inputs;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.models.attributes.InputAttribute;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GroupInput extends PerunEntityInput {

	private static final String ENTITY_ID_FIELD = "group_id";
	private static final String ENTITY_TABLE = "groups";
	private static final String ATTRIBUTES_TABLE = "group_attr_values";
	private static final String ATTRIBUTES_NAMES_TABLE = "attr_names";

	private String name;
	private String description;
	private Long parentGroupId;
	private Long voId;

	public GroupInput(boolean isTopLevel) {
		super(ENTITY_ID_FIELD, ENTITY_TABLE, ATTRIBUTES_TABLE, ATTRIBUTES_NAMES_TABLE, isTopLevel);
	}

	public GroupInput(boolean isTopLevel, String name, String description, Long parentGroupId, Long voId) {
		super(ENTITY_ID_FIELD, ENTITY_TABLE, ATTRIBUTES_TABLE, ATTRIBUTES_NAMES_TABLE, isTopLevel);
		this.name = name;
		this.description = description;
		this.parentGroupId = parentGroupId;
		this.voId = voId;
	}

	public GroupInput(boolean isTopLevel, String name, String description, Long parentGroupId, Long voId,
					  List<InputAttribute> attributes, List<String> attributesNames, List<PerunEntityInput> entities) {
		super(ENTITY_ID_FIELD, ENTITY_TABLE, ATTRIBUTES_TABLE, ATTRIBUTES_NAMES_TABLE, isTopLevel);
		super.getAttributes().addAll(attributes);
		super.getAttributesNames().addAll(attributesNames);
		super.getEntities().addAll(entities);
		this.name = name;
		this.description = description;
		this.parentGroupId = parentGroupId;
		this.voId = voId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(Long parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	public Long getVoId() {
		return voId;
	}

	public void setVoId(Long voId) {
		this.voId = voId;
	}

	@Override
	public JDBCQuery toQuery() {
		return DAOUtils.queryBuilder(isTopLevel, this);
	}
}
