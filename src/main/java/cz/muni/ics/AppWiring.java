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

	private static Map<String, Method> getMethodMap = new HashMap<>();
	private static Map<String, Method> getRichMethodMap = new HashMap<>();
	private static Map<String, Method> getCompleteRichMethodMap = new HashMap<>();

	static {
		for (Method m: Query.class.getMethods()) {
			String name = m.getName();
			if (name.startsWith("getCompleteRich")) {
				getCompleteRichMethodMap.put(m.getName(), m);
			} else if (name.startsWith("getRich")) {
				getRichMethodMap.put(m.getName(), m);
			} else if (name.startsWith("get")) {
				getMethodMap.put(m.getName(), m);
			}
		}
	}

	private static DataFetcher entitiesFetcher = env -> {
		Context ctx = env.getContext();
		String action = env.getField().getName();
		Map<String, Object> args = env.getArguments();
		List<InputAttribute> core = convertInputAttributes(args.get("core"));
		List<InputAttribute> attrs = convertInputAttributes(args.get("attrs"));
		List<String> attrsNames = convertAttrsNames(args.get("attrsNames"));

		return invokeFetchMethod(ctx, action, core, attrs, attrsNames);
	};

	private static Object invokeFetchMethod(Context ctx, String action, List<InputAttribute> core,
											List<InputAttribute> attrs, List<String> attrsNames) {
		if (action.startsWith("getCompleteRich")) {
			Method m = getCompleteRichMethodMap.get(action);
			try {
				return m.invoke(ctx.getQuery(), core, attrsNames);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else if (action.startsWith("getRich")) {
			Method m = getRichMethodMap.get(action);
			try {
				return m.invoke(ctx.getQuery(), core, attrs, attrsNames);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} else if (action.startsWith("get")) {
			Method m = getMethodMap.get(action);
			try {
				return m.invoke(ctx.getQuery(), core);
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
