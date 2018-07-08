package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.IntegerAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.attributes.StringAttribute;
import cz.muni.ics.models.entities.PerunEntity;
import cz.muni.ics.models.richEntities.RichPerunEntity;
import cz.muni.ics.models.richEntities.RichVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DAOUtils {

	private static Properties dbTablesProperties;

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

	public static boolean hasAttributes(RichPerunEntity entity, List<InputAttribute> attrs) {
		Map<String, String> input = getInputAsMap(attrs);
		List<PerunAttribute> attributes = entity.getAttributesByKeys(new ArrayList<>(input.keySet()));
		if (attributes.size() < 1) {
			//no corresponding attributes were found
			return false;
		}

		for (PerunAttribute a: attributes) {
			if (! a.getValue().contains(input.get(a.getKey()))) {
				//corresponding attribute has different value
				return false;
			}
		}

		return true;
	}

	private static Map<String, String> getInputAsMap(List<InputAttribute> attrs) {
		Map<String, String> res = new HashMap<>();
		for (InputAttribute a: attrs) {
			res.put(a.getKey(), a.getValue());
		}

		return res;
	}

	public static String simpleQueryBuilder (String where, PerunEntityType type) {
		String prefix = getEntityPrefix(type);

		String entity = dbTablesProperties.getProperty(prefix + ".entityAs");
		String entityTable = dbTablesProperties.getProperty(prefix + ".entityTable");

		StringBuilder query = new StringBuilder();
		query.append("SELECT to_jsonb(t)");
		query.append(" AS ").append(entity);
		query.append(" FROM ").append(entityTable).append(" t");
		if (where != null) {
			query.append(' ').append(where.trim());
		}
		query.append(" GROUP BY t.id");

		return query.toString();
	}

	public static String queryBuilder(String entityWhere, String attrsWhere, PerunEntityType type) {
		String prefix = getEntityPrefix(type);

		String entity = dbTablesProperties.getProperty(prefix + ".entityAs");
		String entityTable = dbTablesProperties.getProperty(prefix + ".entityTable");
		String attrValues = dbTablesProperties.getProperty(prefix + ".attrValuesTable");
		String entityId = dbTablesProperties.getProperty(prefix + ".attrValuesEntityId");
		String attrNames = dbTablesProperties.getProperty(prefix + ".attrNamesTable");

		StringBuilder query = new StringBuilder();
		query.append("SELECT to_jsonb(t) || jsonb_build_object(");
		query.append("'attributes', json_agg(");
		query.append("jsonb_build_object('key', friendly_name,'val', attr_value, 'val_text', attr_value_text, 'type', type)))");
		query.append(" AS ").append(entity);
		query.append(" FROM ").append(entityTable).append(" t");
		query.append(" JOIN (");
		query.append("SELECT ").append(entityId).append(" friendly_name, attr_value, attr_value_text, type");
		query.append(" FROM ").append(attrValues).append(" av JOIN ").append(attrNames).append(" an");
		query.append(" ON av.attr_id = an.id");
		if (attrsWhere != null) {
			query.append(" ").append(attrsWhere);
		}
		query.append(") AS attrs ON t.id = attr.").append(entityId);
		if (entityWhere != null) {
			query.append(" ").append(entityWhere);
		}
		query.append(" GROUP BY t.id");

		return query.toString();
	}

	public static String attributeWhereBuilder(List<InputAttribute> input) {
		StringBuilder query = new StringBuilder();

		query.append("WHERE");
		query.append(resolveSubQuery(input.get(0)));
		for (int i = 1; i < input.size(); i++) {
			query.append(" OR ");
			query.append(resolveSubQuery(input.get(i)));
		}

		return query.toString();
	}

	public static String resolveSubQuery(InputAttribute inputAttribute) {
		//TODO: used operator??? LIKE vs. =
		String subquery;
		if (inputAttribute.getKey() == null) {
			subquery = "(attr_value LIKE ? OR attr_value_text LIKE ?)";
		} else if (inputAttribute.getValue() == null) {
			subquery = "(friendlyName LIKE ?)";
		} else {
			subquery = "(friendlyName LIKE ? AND (attr_value LIKE ? OR attr_value_text LIKE ?))";
		}

		return subquery;
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

}
