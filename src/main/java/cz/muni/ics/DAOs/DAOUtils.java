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

	public static String queryBuilder(String where, boolean withAttrs, PerunEntityType type) {
		String prefix = getEntityPrefix(type);

		String entity = dbTablesProperties.getProperty(prefix + ".entityAs");
		String entityTable = dbTablesProperties.getProperty(prefix + ".entityTable");
		String attrValues = dbTablesProperties.getProperty(prefix + ".attrValuesTable");
		String attrNames = dbTablesProperties.getProperty(prefix + ".attrNamesTable");

		StringBuilder query = new StringBuilder();
		query.append("SELECT to_jsonb(t)");
		if (withAttrs) {
			query.append(" ||");
			query.append(" jsonb_build_object('attributes', json_agg(jsonb_build_object('key', friendly_name," +
					" 'val', attr_value, 'val_text', attr_value_text, 'type', type)))");
		}
		query.append(" AS ").append(entity);
		query.append(" FROM ").append(entityTable).append(" t");
		if (withAttrs) {
			query.append(" JOIN ").append(attrValues).append(" av ON av.vo_id = t.id");
			query.append(" JOIN ").append(attrNames). append(" an ON an.id = av.attr_id");
		}
		if (where != null) {
			query.append(' ').append(where.trim());
		}
		query.append(" GROUP BY t.id");
		return query.toString();
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
