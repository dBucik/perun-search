package cz.muni.ics.models.inputs;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.models.attributes.InputAttribute;

import javax.print.attribute.standard.MediaSize;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupInput extends PerunEntityInput {

	private static final String NAME = "group";
	private static final String ENTITY_ID_FIELD = "group_id";
	private static final String ENTITY_TABLE = "groups";
	private static final String ATTRIBUTES_TABLE = "group_attr_values";
	private static final String ATTRIBUTES_NAMES_TABLE = "attr_names";

	public GroupInput(boolean isTopLevel) {
		super(ENTITY_ID_FIELD, ENTITY_TABLE, ATTRIBUTES_TABLE, ATTRIBUTES_NAMES_TABLE, isTopLevel);
	}

	public GroupInput(boolean isTopLevel, List<InputAttribute> attributes, List<String> attributesNames,
					  Map<String, PerunEntityInput> entities) {
		super(ENTITY_ID_FIELD, ENTITY_TABLE, ATTRIBUTES_TABLE, ATTRIBUTES_NAMES_TABLE, isTopLevel);
		super.getAttributes().addAll(attributes);
		super.getAttributesNames().addAll(attributesNames);
		super.getEntities().putAll(entities);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public JDBCQuery toQuery() {
		return DAOUtils.queryBuilder(isTopLevel, this);
	}
}
