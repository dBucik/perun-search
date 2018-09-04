package cz.muni.ics;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Query;
import graphql.schema.DataFetcher;
import org.dataloader.DataLoaderRegistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AppWiring {

	public static class Context {

		private final DataLoaderRegistry dataLoaderRegistry;
		private final Query query;

		public Context(Query query) {
			this.query = query;
			this.dataLoaderRegistry = new DataLoaderRegistry();
		}

		public DataLoaderRegistry getDataLoaderRegistry() {
			return dataLoaderRegistry;
		}

		public Query getQuery() {
			return query;
		}

	}

	private static Map<String, Method> queriesMap = new HashMap<>();

	static {
		for (Method m: Query.class.getMethods()) {
			queriesMap.put(m.getName(), m);
		}
	}

	private static DataFetcher entitiesFetcher = env -> {
		Context ctx = env.getContext();
		String action = env.getField().getName();
		Map<String, Object> args = env.getArguments();
		List<InputAttribute> attrs = convertInputAttributes(args.get("attributes"));
		List<String> attrsNames = convertAttrsNames(args.get("attrsNames"));

		return invokeFetchMethod(ctx, action, attrsNames, attrs);
	};

	private static Object invokeFetchMethod(Context ctx, String action, List<String> attrsNames,
											List<InputAttribute> attrs) {
		if (action.startsWith("get")) {
			Method m = queriesMap.get(action);
			try {
				return m.invoke(ctx.getQuery(), attrsNames, attrs);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return Collections.EMPTY_LIST;
	}

	public static DataFetcher getEntitiesFetcher() {
		return entitiesFetcher;
	}

	private static List<InputAttribute> convertInputAttributes(Object input) {
		if (input == null) {
			return null;
		}

		ArrayList list = (ArrayList) input;
		List<InputAttribute> res = new LinkedList<>();

		for (Object attrObj : list) {
			Map<String, String> oAsMap = (Map<String, String>) attrObj;
			InputAttribute attr = new InputAttribute();
			attr.setKey(oAsMap.get("key"));
			attr.setType(oAsMap.get("type"));
			attr.setValue(oAsMap.get("value"));
			res.add(attr);
		}

		return res;
	}

	private static List<String> convertAttrsNames(Object input) {
		if (input == null) {
			return null;
		}

		ArrayList list = (ArrayList) input;
		List<String> res = new LinkedList<>();

		for (Object str : list) {
			String val = (String) str;
			res.add(val);
		}

		return res;
	}

}
