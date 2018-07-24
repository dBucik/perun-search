package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.Relation;
import cz.muni.ics.models.RelationType;
import cz.muni.ics.models.attributes.InputAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.StringJoiner;

public class DAOUtils {

	private static final Logger log = LoggerFactory.getLogger(DAOUtils.class);

	private static Properties dbTablesProperties;

	private static final String MATCH_TEXT = "LIKE";
	private static final String MATCH_TYPE = "=";
	private static final String GET_AS_TEXT = "->>";
	private static final String GET_AS_TYPE = "->";

	public static final String NO_WHERE = null;
	public static final List<String> NO_ATTRS_NAMES = null;
	public static final int NO_ATTRS_NAMES_COUNT = 0;
	public static final List<InputAttribute> NO_ATTRS = null;

	static {
		log.debug("Reading db_tables.properties");
		dbTablesProperties = new Properties();
		try {
			dbTablesProperties.load(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("db_tables.properties")
			);
		} catch (IOException e) {
			log.error("Cannot read db_tables.properties. Reason: {}", e);
			throw new RuntimeException("Cannot read db_tables.properties");
		}
	}

	public static String simpleQueryBuilder(String where, PerunEntityType type) {
		log.trace("Building simple query (where: {}, type: {})", where, type);
		return entityQueryBuilder(true, null, where, type);
	}

	public static String complexQueryBuilder(String innerWhere, String outerWhere, PerunEntityType type) {
		log.trace("Building complex query (innerWhere: {}, outerWhere: {}, type: {})", innerWhere, outerWhere, type);
		return entityQueryBuilder(false, innerWhere, outerWhere, type);
	}

	public static String relationQueryBuilder(String where, RelationType type) {
		log.trace("Building relation query (where: {}, type: {}", where, type);
		String prefix = getRelationPrefix(type);

		String relationTable = dbTablesProperties.getProperty(prefix + ".relationTable");
		String primaryEntityId = dbTablesProperties.getProperty(prefix + ".primaryEntityId");
		String secondaryEntityId = dbTablesProperties.getProperty(prefix + ".secondaryEntityId");
		String attrNames = dbTablesProperties.getProperty(prefix + ".attrNamesTable");

		StringBuilder query = new StringBuilder();
		query.append("SELECT ? AS rel_type, ").append(primaryEntityId).append(", ").append(secondaryEntityId).append(",")
				.append(" json_object_agg(friendly_name, json_build_object(")
				.append("'value', attr_value, 'value_text', attr_value_text, 'type', type)) AS attributes")
				.append(" FROM ").append(relationTable).append(" av JOIN ").append(attrNames).append(" an ON av.attr_id = an.id");
		if (!Objects.equals(where, NO_WHERE)) {
			query.append(' ').append(where);
		}
		query.append(" GROUP BY ").append(primaryEntityId).append(", ").append(secondaryEntityId);

		log.trace("Built query: {}", query.toString());
		return query.toString();
	}

	private static String entityQueryBuilder(boolean isSimple, String innerWhere, String outerWhere, PerunEntityType type) {
		log.trace("Building query (isSimple: {}, innerWhere: {}, outerWhere: {}, type: {}",
				isSimple, innerWhere, outerWhere, type);
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
					.append(" FROM ").append(attrValues).append(" av JOIN ").append(attrNames)
					.append(" an ON av.attr_id = an.id");
			if (!Objects.equals(innerWhere, NO_WHERE)) { // select specific attributes by names
				query.append(' ').append(innerWhere);
			}
			query.append(" GROUP BY ").append(entityId) // group attributes by entity id and add join condition
					.append(") AS attributes ON ent.id = attributes.").append(entityId);
		}
		if (!Objects.equals(NO_WHERE, outerWhere)) { // add conditions for entity and attributes
			query.append(' ').append(outerWhere);
		}

		log.trace("Built query: {}", query.toString());
		return query.toString();
	}

	public static String innerWhereBuilder(int namesCount) {
		log.trace("Building inner where (namesCount: {})", namesCount);
		if (namesCount <= NO_ATTRS_NAMES_COUNT) {
			log.trace("No attrs_names provided, returning NULL");
			return NO_WHERE;
		}

		String query = "WHERE ";
		StringJoiner sj = new StringJoiner(" OR ");
		for (int i = 0; i < namesCount; i++) {
			sj.add("friendly_name = ?");
		}

		log.trace("Built inner WHERE: {}", query + sj.toString());
		return query + sj.toString();
	}

	public static String outerWhereBuilder(List<InputAttribute> core, List<InputAttribute> attrs) {
		log.trace("Building outer where (core: {}, attrs: {})", core, attrs);
		if ((core == null || core.isEmpty()) && (attrs == null || attrs.isEmpty())) {
			log.trace("No attrs provided, returning NULL");
			return null;
		}

		String query = "WHERE ";
		StringJoiner sj = new StringJoiner(" AND ");
		if (core != null) {
			for (InputAttribute a : core) {
				String op = resolveMatchOperator(a.getAttributeType());
				sj.add("ent." + a.getKey() + ' ' + op + " ?"); // TODO: escape name to prevent SQL injection
			}
		}

		if (attrs != null) {
			for (InputAttribute a : attrs) {
				String op1 = resolveFetchOperator(a.getAttributeType());
				String op2 = resolveMatchOperator(a.getAttributeType());
				sj.add("attributes.data" + op1 + '\'' + a.getKey() + "' " + op2 + " ?"); // TODO: escape name to prevent SQL injection
			}
		}

		log.trace("Built outer WHERE: {}", query + sj.toString());
		return query + sj.toString();
	}

	public static String relationWhereBuilder(RelationType type, InputAttribute primary, InputAttribute secondary,
											  int namesCount, List<InputAttribute> attrs) {
		log.trace("Building relation where (type: {}, primary: {}, secondary: {}, attrs: {})", type, primary, secondary, attrs);
		if ((primary == null) && (secondary == null) && (attrs == null || attrs.isEmpty())) {
			log.trace("No attrs provided, returning NULL");
			return null;
		}

		String query = "WHERE ";
		StringJoiner sj = new StringJoiner(" AND ");
		StringJoiner sub = new StringJoiner(" OR ");

		if (primary != null) {
			String primaryId = Relation.resolvePrimaryEntityKeyFromRelationType(type);
			String op = resolveMatchOperator(primary.getAttributeType());
			sj.add(primaryId + " " + op + " ?");
		}

		if (secondary != null) {
			String secondaryId = Relation.resolveSecondaryEntityKeyFromRelationType(type);
			String op = resolveMatchOperator(secondary.getAttributeType());
			sj.add(secondaryId + " " + op + " ?");
		}

		if (namesCount > NO_ATTRS_NAMES_COUNT) {
			for (int i = 0; i < namesCount; i++) {
				sub.add("(friendly_name = ?)");
			}
		}

		if (attrs != null) {
			for (InputAttribute a : attrs) {
				String op = resolveMatchOperator(a.getAttributeType());
				sub.add("((friendly_name = ?) AND (attr_value" + op + "?) OR (attr_value_text" + op + "?))");
			}
		}

		sj.add(sub.toString());
		log.trace("Built outer WHERE: {}", query + sj.toString());
		return query + sj.toString();
	}

	public static Object[] buildEntityParams(List<String> attrsNames, List<InputAttribute> core, List<InputAttribute> attrs) {
		log.trace("Building params array (attrsNames: {}, core: {}, attrs: {})");

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

		log.trace("Built params: {}", params);
		return params.toArray();
	}

	public static Object[] buildRelationParams(String type, InputAttribute primary, InputAttribute secondary, List<String> attrsNames,
											   List<InputAttribute> attrs) {
		List<Object> res = new LinkedList<>();

		res.add(type);

		if (primary != null) {
			res.add(resolveTrueValue(primary.getAttributeType(), primary.getValue()));
		}

		if (secondary != null) {
			res.add(resolveTrueValue(secondary.getAttributeType(), secondary.getValue()));
		}

		if (attrsNames != NO_ATTRS_NAMES) {
			res.addAll(attrsNames);
		}

		if (attrs != NO_ATTRS) {
			for (InputAttribute a: attrs) {
				res.add(a.getKey());
				res.add(resolveTrueValue(a.getAttributeType(), a.getValue()));
			}
		}

		return res.toArray();
	}

	private static List<Object> addValuesFromInputAttrs(List<InputAttribute> attrs) {
		List<Object> res = new LinkedList<>();
		for (InputAttribute a: attrs) {
			String op = resolveMatchOperator(a.getAttributeType());
			if (op.equals(MATCH_TEXT)) {
				res.add('%' + a.getValue() + '%');

			} else {
				Object val = resolveTrueValue(a.getAttributeType(), a.getValue());

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

	private static String getRelationPrefix(RelationType type) {
		switch(type) {
			case FACILITY_OWNER: return "facility_owner";
			case GROUP_MEMBER: return "group_member";
			case GROUP_RESOURCE: return "group_resource";
			case MEMBER_GROUP: return "member_group";
			case MEMBER_RESOURCE: return "member_resource";
			case RESOURCE_SERVICE: return "resource_service";
			case USER_FACILITY: return "user_facility";
			case GROUP_EXT_SOURCE: return "group_ext_source";
			case VO_EXT_SOURCE: return "vo_ext_source";
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
