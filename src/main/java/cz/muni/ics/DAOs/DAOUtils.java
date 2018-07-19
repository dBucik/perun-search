package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.InputAttributeType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.StringJoiner;

public class DAOUtils {

	private static Properties dbTablesProperties;
	private static String MATCH_TEXT = "LIKE";
	private static String MATCH_TYPE = "=";
	private static String GET_AS_TEXT = "->>";
	private static String GET_AS_TYPE = "->";

	public static final String NO_WHERE = null;
	public static final List<String> NO_ATTRS_NAMES = null;
	public static final int NO_ATTRS_NAMES_COUNT = 0;
	public static final List<InputAttribute> NO_ATTRS = null;

	static {
		dbTablesProperties = new Properties();
		try {
			dbTablesProperties.load(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("db_tables.properties")
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String simpleQueryBuilder(String where, PerunEntityType type) {
		return queryBuilder(true, null, where, type);
	}

	public static String complexQueryBuilder(String innerWhere, String outerWhere, PerunEntityType type) {
		return queryBuilder(false, innerWhere, outerWhere, type);
	}

	private static String queryBuilder(boolean isSimple, String innerWhere, String outerWhere, PerunEntityType type) {
		String prefix = getEntityPrefix(type);

		String entityTable = dbTablesProperties.getProperty(prefix + ".entityTable");
		String attrValues = dbTablesProperties.getProperty(prefix + ".attrValuesTable");
		String entityId = dbTablesProperties.getProperty(prefix + ".attrValuesEntityId");
		String attrNames = dbTablesProperties.getProperty(prefix + ".attrNamesTable");

		StringBuilder query = new StringBuilder();
		query.append("SELECT to_json(ent) AS entity"); //select entity
		if (! isSimple) {
			query.append(", attributes.data AS attrs"); //select also attributes
		}
		query.append(" FROM ").append(entityTable).append(" ent"); //entity table
		if (! isSimple) {
			query.append(" JOIN (") // select attributes and build JSON
					.append("SELECT ").append(entityId).append(", json_object_agg(friendly_name, json_build_object(")
					.append("'value', attr_value, 'value_text', attr_value_text, 'type', type)) AS data")
					.append(" FROM ").append(attrValues).append(" av JOIN ").append(attrNames).append(" an ON av.attr_id = an.id");
			if (!Objects.equals(innerWhere, NO_WHERE)) { // select specific attributes by names
				query.append(' ').append(innerWhere);
			}
			query.append(" GROUP BY ").append(entityId) // group attributes by entity id and add join condition
					.append(") AS attributes ON ent.id = attributes.").append(entityId);
		}
		if (!Objects.equals(NO_WHERE, outerWhere)) { // add conditions for entity and attributes
			query.append(' ').append(outerWhere);
		}


		return query.toString();
	}

	public static String innerWhereBuilder(int namesCount) {
		if (namesCount <= NO_ATTRS_NAMES_COUNT) {
			return null;
		}

		String query = "WHERE ";
		StringJoiner sj = new StringJoiner(" OR ");
		for (int i = 0; i < namesCount; i++) {
			sj.add("friendly_name = ?");
		}

		return query + sj.toString();
	}

	public static String outerWhereBuilder(List<InputAttribute> core, List<InputAttribute> attrs) {
		if ((core == null || core.isEmpty()) && (attrs == null || attrs.isEmpty())) {
			return null;
		}

		String query = "WHERE ";
		StringJoiner sj = new StringJoiner(" AND ");
		if (core != null) {
			for (InputAttribute a : core) {
				String op = resolveMatchOperator(a.getType1());
				sj.add("ent." + a.getKey() + ' ' + op + " ?"); // TODO: escape name to prevent SQL injection
			}
		}

		if (attrs != null) {
			for (InputAttribute a : attrs) {
				String op1 = resolveFetchOperator(a.getType1());
				String op2 = resolveMatchOperator(a.getType1());
				sj.add("attributes.data" + op1 + '\'' + a.getKey() + "' " + op2 + " ?"); // TODO: escape name to prevent SQL injection
			}
		}

		return query + sj.toString();
	}

	public static Object[] buildParams(List<String> attrsNames, List<InputAttribute> core, List<InputAttribute> attrs) {
		List<Object> params = new LinkedList<>();
		if (attrsNames != NO_ATTRS_NAMES) {
			params.addAll(attrsNames); // add attributes names to be fetched

		}

		if (attrs != NO_ATTRS) {
			for (InputAttribute a: attrs) {
				params.add(a.getKey()); // add attributes names to be fetched and later used in outer WHERE

			}
		}

		if (core != NO_ATTRS) { // add core attributes values to outer WHERE
			params.addAll(addValuesFromInputAttrs(core));
		}

		if (attrs != NO_ATTRS) { // add attributes values to outer WHERE
			params.addAll(addValuesFromInputAttrs(attrs));
		}

		return params.toArray();
	}

	private static List<Object> addValuesFromInputAttrs(List<InputAttribute> attrs) {
		List<Object> res = new LinkedList<>();
		for (InputAttribute a: attrs) {
			String op = resolveMatchOperator(a.getType1());
			if (op.equals(MATCH_TEXT)) {
				res.add('%' + a.getValue() + '%');

			} else {
				Object val = resolveTrueValue(a.getType1(), a.getValue());

				res.add(val);
			}
		}

		return res;
	}

	private static Object resolveTrueValue(InputAttributeType type, String value) {
		if (type == null) {
			return value;
		}

		switch (type) {
			case INTEGER: return Integer.parseInt(value);
			case BOOLEAN: return Boolean.parseBoolean(value);
			default: return value;
		}
	}

	private static String getEntityPrefix(PerunEntityType type) {
		switch (type) {
			case VO: return "vo";
			case HOST: return "host";
			case USER: return "user";
			case GROUP: return "group";
			case OWNER: return "owner";
			case MEMBER: return "member";
			case SERVICE: return "service";
			case FACILITY: return "facility";
			case RESOURCE: return "resource";
			case EXT_SOURCE: return "extSource";
			case USER_EXT_SOURCE: return "userExtSource";
			default: return "";
		}
	}

	private static String resolveFetchOperator(InputAttributeType type) {
		if (type == null) {
			return GET_AS_TEXT;
		}

		switch (type) {
			case STRING:
			case MAP:
			case ARRAY:
			case LARGE_LIST:
			case LARGE_STRING:
			case EXACT_STRING:
			case EXACT_LARGE_STRING:
				return GET_AS_TEXT;
			case BOOLEAN:
			case INTEGER:
			default:
				return GET_AS_TYPE;
		}
	}

	private static String resolveMatchOperator(InputAttributeType type) {
		if (type == null) {
			return MATCH_TEXT;
		}

		switch (type) {
			case STRING:
			case MAP:
			case ARRAY:
			case LARGE_LIST:
			case LARGE_STRING:
				return MATCH_TEXT;
			case EXACT_STRING:
			case BOOLEAN:
			case INTEGER:
			case EXACT_LARGE_STRING:
			default:
				return MATCH_TYPE;
		}
	}

}
