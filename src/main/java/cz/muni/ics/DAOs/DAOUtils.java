package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.attributes.IntegerAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.attributes.StringAttribute;
import cz.muni.ics.models.entities.PerunEntity;
import cz.muni.ics.models.richEntities.RichPerunEntity;
import cz.muni.ics.models.richEntities.RichVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOUtils {

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

}
