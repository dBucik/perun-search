package cz.muni.ics.DAOs;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class JDBCQuery {

	private String queryString;
	private MapSqlParameterSource paramsMap = new MapSqlParameterSource();
	private int paramCounter = 0;

	public JDBCQuery() { }

	public JDBCQuery(String queryString, MapSqlParameterSource paramsMap) {
		this.queryString = queryString;
		this.paramsMap = paramsMap;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public MapSqlParameterSource getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(MapSqlParameterSource paramsMap) {
		this.paramsMap = paramsMap;
	}

	public void addParam(Object value) {
		paramsMap.addValue(String.valueOf(':' + paramCounter), value);
	}

	public String getNextParamName() {
		paramCounter++;
		return String.valueOf(':' + paramCounter);
	}
}
