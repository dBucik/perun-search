package cz.muni.ics;

import cz.muni.ics.exceptions.InputParsingException;
import cz.muni.ics.models.attributes.InputAttribute;
import cz.muni.ics.models.inputs.GroupInput;
import cz.muni.ics.models.inputs.PerunEntityInput;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonToInputParser {

	public static final String[] GROUP_CORE = new String[] { "id", "name", "description", "parentGroupId", "voId"};

	private static PerunEntityInput parse(JSONObject json, boolean isTopLevel) throws InputParsingException {
		if (json.has("group")) {
			return parseGroup(json.getJSONObject("group"), isTopLevel);
		}

		return null;
	}

	public static PerunEntityInput parse(JSONObject json) throws InputParsingException {
		return parse(json, true);
	}

	public static GroupInput parseGroup(JSONObject json, boolean isTopLevel) throws InputParsingException {
		JSONArray coreJson = json.getJSONArray("core");
		JSONArray attributesJson = json.getJSONArray("attributes");
		JSONArray attributesNamesJson = json.getJSONArray("attributesNames");
		JSONArray entitiesJson = json.getJSONArray("entities");

		List<InputAttribute> core = parseCoreJson(coreJson, Arrays.asList(GROUP_CORE));
		List<InputAttribute> attributes = parseAttributesJson(attributesJson);
		List<String> attributesNames = parseAttributesNamesJson(attributesNamesJson);
		Map<String,PerunEntityInput> entities = parseEntitiesJson(entitiesJson);

		GroupInput res = new GroupInput(isTopLevel);
		res.setCore(core);
		res.setAttributes(attributes);
		res.setAttributesNames(attributesNames);
		res.setEntities(entities);

		return res;
	}

	private static List<InputAttribute> parseCoreJson(JSONArray coreJson, List<String> allowed) throws InputParsingException {
		List<InputAttribute> core = new ArrayList<>();
		for (int i = 0; i < coreJson.length(); i++) {
			JSONObject obj = coreJson.getJSONObject(i);
			String key = obj.getString("key");
			if (! allowed.contains(key)) {
				throw new InputParsingException("Key for core attributes not listed in allowed values.");
			}
			core.add(mapAttribute(obj));
		}

		return core;
	}

	private static List<InputAttribute> parseAttributesJson(JSONArray attributesJson) throws InputParsingException {
		List<InputAttribute> attributes = new ArrayList<>();
		for (int i = 0; i < attributesJson.length(); i++) {
			JSONObject obj = attributesJson.getJSONObject(i);
			attributes.add(mapAttribute(obj));
		}

		return attributes;
	}

	private static List<String> parseAttributesNamesJson(JSONArray attributesNamesJson) {
		List<String> attributesNames = new ArrayList<>();
		for (int i = 0; i < attributesNamesJson.length(); i++) {
			attributesNames.add(attributesNamesJson.getString(i));
		}

		return attributesNames;
	}

	private static Map<String, PerunEntityInput> parseEntitiesJson(JSONArray entitiesJson) throws InputParsingException {
		Map<String, PerunEntityInput> entities = new HashMap<>();
		for (int i = 0; i < entitiesJson.length(); i++) {
			JSONObject obj = entitiesJson.getJSONObject(i);
			PerunEntityInput res = parse(obj, false);
			if (res == null) {
				continue;
			}
			entities.put(res.getName(), res);
		}


		return entities;
	}

	private static InputAttribute mapAttribute(JSONObject obj) throws InputParsingException {
		String key = obj.getString("key");
		String type = obj.getString("type");
		String value = obj.getString("value");

		return new InputAttribute(key, type, value);
	}

	/*
	public static ExtSourceInput parseExtSource(JSONObject json) {
		return null;
	}

	public static FacilityInput parseFacility(JSONObject json) {
		return null;
	}

	public static HostInput parseHost(JSONObject json) {
		return null;
	}

	public static MemberInput parseMember(JSONObject json) {
		return null;
	}

	public static ResourceInput parseResource(JSONObject json) {
		return null;
	}

	public static ServiceInput parseService(JSONObject json) {
		return null;
	}

	public static UserInput parseUser(JSONObject json) {
		return null;
	}

	public static UserExtSourceInput parseUserExtSource(JSONObject json) {
		return null;
	}

	public static VoInput parseVo(JSONObject json) {
		return null;
	}
	*/
}
