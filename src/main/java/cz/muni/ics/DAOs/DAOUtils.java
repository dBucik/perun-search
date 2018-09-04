package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.Relation;
import cz.muni.ics.models.RelationType;
import cz.muni.ics.models.attributes.enums.InputAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.StringTokenizer;

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

	public static JDBCQuery entityQueryBuilder(PerunEntityType entityType, List<String> attrsNames,
												List<InputAttribute> attrs) {
		log.trace("Building query (entityType: {}, attrsNames: {}, attrs: {}",
				entityType, attrsNames, attrs);

		return entityQueryBuilder(attrsNames, attrs, entityType);
	}

	private static JDBCQuery entityQueryBuilder(List<String> attrsNames, List<InputAttribute> attrs,
												PerunEntityType type) {
		JDBCQuery query = new JDBCQuery();
		log.trace("Building query (attrsNames: {}, attrs: {}, type: {}", attrsNames, attrs, type);
		String prefix = getEntityPrefix(type);

		String attrValues = dbTablesProperties.getProperty(prefix + ".attrValuesTable");
		String entityId = dbTablesProperties.getProperty(prefix + ".attrValuesEntityId");
		String attrNames = dbTablesProperties.getProperty(prefix + ".attrNamesTable");

		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT attributes.data AS attrs");
		queryString.append(" FROM ")
				.append("SELECT ").append(entityId).append(", json_object_agg(friendly_name, json_build_object(")
				.append("'value', attr_value, 'value_text', attr_value_text, 'type', type, 'name', attr_name, 'namespace', an.namespace)) AS data")
				.append(" FROM ").append(attrValues).append(" av JOIN ").append(attrNames)
				.append(" an ON av.attr_id = an.id");
		String innerWhere = buildInnerWhere(query, attrsNames);
		if (!Objects.equals(NO_WHERE, innerWhere)) { // select specific attributes by names
			queryString.append(' ').append(innerWhere);
		}
		queryString.append(" GROUP BY ").append(entityId) // group attributes by entity id and add join condition
				.append(") AS attributes ON ent.id = attributes.").append(entityId);

		String outerWhere = buildOuterWhere(query, attrs);
		if (!Objects.equals(NO_WHERE, outerWhere)) { // add conditions for entity and attributes
			queryString.append(' ').append(outerWhere);
		}

		query.setQueryString(queryString.toString());
		log.trace("Built query: {}", query.getQueryString());
		return query;
	}

	public static JDBCQuery relationQueryBuilder(Long primaryId, Long secondaryId, List<String> attrsNames,
												 List<InputAttribute> attrs , RelationType type) {
		JDBCQuery query = new JDBCQuery();
		log.trace("Building query (primaryId: {}, secondaryId: {}, attrsNames: {}, attrs: {}, type: {}",
				primaryId, secondaryId, attrsNames, attrs, type);
		String prefix = getRelationPrefix(type);

		String relationTable = dbTablesProperties.getProperty(prefix + ".relationTable");
		String primaryEntityId = dbTablesProperties.getProperty(prefix + ".primaryEntityId");
		String secondaryEntityId = dbTablesProperties.getProperty(prefix + ".secondaryEntityId");
		String attrNames = dbTablesProperties.getProperty(prefix + ".attrNamesTable");

		StringBuilder queryString = new StringBuilder();

		queryString.append("SELECT ? AS rel_type, ").append(primaryEntityId).append(", ").append(secondaryEntityId).append(",")
				.append(" json_object_agg(friendly_name, json_build_object(")
				.append("'value', attr_value, 'value_text', attr_value_text, 'type', type, 'name', attr_name, 'namespace', an.namespace)) AS attributes")
				.append(" FROM ").append(relationTable).append(" av JOIN ").append(attrNames).append(" an ON av.attr_id = an.id");
		String where = buildRelationWhere(query, type, primaryId, secondaryId, attrsNames, attrs);

		if (!Objects.equals(where, NO_WHERE)) {
			queryString.append(' ').append(where);
		}
		queryString.append(" GROUP BY ").append(primaryEntityId).append(", ").append(secondaryEntityId);

		query.setQueryString(queryString.toString());
		log.trace("Built query: {}", query.getQueryString());
		return query;
	}

	private static String buildOuterWhere(JDBCQuery query, List<InputAttribute> attrs) {
		log.trace("Building outer where (query: {}, core: {}, attrs: {})", query, attrs);
		if (attrs == null || attrs.isEmpty()) {
			log.trace("No attrs provided, returning NULL");
			return NO_WHERE;
		}

		String queryString = "WHERE ";
		StringJoiner sj = new StringJoiner(" AND ");
		for (InputAttribute a : attrs) {
			String op1 = resolveFetchOperator(a.getAttributeType());
			String op2 = resolveMatchOperator(a.getAttributeType());
			sj.add("attributes.data" + op1 + '\'' + a.getKey() + "' " + op2 + " (" + query.getNextParamName() + ')');// TODO: escape name to prevent SQL injection
			query.addParam(resolveTrueValue(a.getAttributeType(), a.getValue()));
		}

		log.trace("Built outer WHERE: {}", query + sj.toString());
		return queryString + sj.toString();
	}

	private static String buildInnerWhere(JDBCQuery query, List<String> attrsNames) {
		log.trace("Building inner where (query: {}, attrsNames: {})", query, attrsNames);
		if (attrsNames == null || attrsNames.size() <= NO_ATTRS_NAMES_COUNT) {
			log.trace("No attrs_names provided, returning NULL");
			return NO_WHERE;
		}

		String queryString = "WHERE ";
		StringJoiner sj = new StringJoiner(" OR ");
		for (String attr: attrsNames) {
			sj.add("friendly_name = " + query.getNextParamName());
			query.addParam(attr);
		}

		log.trace("Built inner WHERE: {}", query + sj.toString());
		return queryString + sj.toString();
	}

	private static String buildRelationWhere(JDBCQuery query, RelationType type, Long primaryId, Long secondaryId,
											List<String> attrsNames, List<InputAttribute> attrs) {
		log.trace("Building relation where (query: {}, type: {}, primaryId: {}, secondaryId: {}, attrsNames: {}, attrs: {})",
				query, type, primaryId, secondaryId, attrsNames, attrs);
		if ((primaryId == null) && (secondaryId == null) && (attrs == null || attrs.isEmpty())) {
			log.trace("No attrs provided, returning NULL");
			return null;
		}

		String queryString = "WHERE ";
		StringJoiner sj = new StringJoiner(" AND ");
		StringJoiner sub = new StringJoiner(" OR ");

		if (primaryId != null) {
			String primary = Relation.resolvePrimaryEntityKeyFromRelationType(type);
			sj.add(primary + " = " + query.getNextParamName());
			query.addParam(primaryId);
		}

		if (secondaryId != null) {
			String secondary = Relation.resolveSecondaryEntityKeyFromRelationType(type);
			sj.add(secondary + " = " + query.getNextParamName());
			query.addParam(secondaryId);
		}
		
		if (attrsNames != null) {
			for (String attrName : attrsNames) {
				sub.add("(friendly_name = " + query.getNextParamName() + ')');
				query.addParam(attrName);
			}
		}

		if (attrs != null) {
			for (InputAttribute a : attrs) {
				String op = resolveMatchOperator(a.getAttributeType());
				String partial = "";
				Object value = resolveTrueValue(a.getAttributeType(), a.getValue());
				partial += "((friendly_name = " + query.getNextParamName();
				query.addParam(a.getKey());
				partial += ") AND (attr_value" + op + query.getNextParamName();
				query.addParam(value);
				partial += ") OR (attr_value_text" + op + query.getNextParamName() + "))";
				query.addParam(value);
				sub.add(partial);
			}
		}
		if (sub.length() > 0) {
			sj.add(sub.toString());
		}

		log.trace("Built outer WHERE: {}", query + sj.toString());
		queryString += sj.toString();
		return queryString;
	}
	
	private static Object resolveTrueValue(InputAttributeType type, String value) {
		if (type == null) {
			return value;
		}

		switch (type) {
			case INTEGER: return Integer.parseInt(value);
			case BOOLEAN: return Boolean.parseBoolean(value);
			case ID: {
				List<Long> arr = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(value, ",");
				while(st.hasMoreTokens())
					arr.add(Long.parseLong(st.nextToken()));
				return arr.toArray();
			}
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
			case ID:
				return "IN";
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
