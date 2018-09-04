package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.DAOs.PerunDAO;
import cz.muni.ics.mappers.MappersUtils;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.entities.Owner;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.entities.Vo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.mappers.MappersUtils.EXT_SOURCE_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.FACILITY_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.GROUP_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.HOST_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.MEMBER_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.OWNER_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.RESOURCE_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.SERVICE_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.USER_EXT_SOURCE_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.USER_ROW_MAPPER;
import static cz.muni.ics.mappers.MappersUtils.VO_ROW_MAPPER;

public class PerunDAOImpl implements PerunDAO {

	private static final Logger log = LoggerFactory.getLogger(PerunDAOImpl.class);

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		if (dataSource == null) {
			throw new IllegalArgumentException("NULL dataSource provided");
		}

		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<ExtSource> getExtSources(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.EXT_SOURCE, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), EXT_SOURCE_ROW_MAPPER);
	}

	@Override
	public List<Facility> getFacilities(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.FACILITY, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), FACILITY_ROW_MAPPER);
	}

	@Override
	public List<Group> getGroups(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.GROUP, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), GROUP_ROW_MAPPER);
	}

	@Override
	public List<Host> getHosts(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.HOST, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), HOST_ROW_MAPPER);
	}

	@Override
	public List<Member> getMembers(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.MEMBER, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MEMBER_ROW_MAPPER);
	}

	@Override
	public List<Owner> getOwners(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.OWNER, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), OWNER_ROW_MAPPER);
	}

	@Override
	public List<Resource> getResources(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.RESOURCE, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RESOURCE_ROW_MAPPER);
	}

	@Override
	public List<Service> getServices(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.SERVICE, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), SERVICE_ROW_MAPPER);
	}

	@Override
	public List<User> getUsers(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.USER, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), USER_ROW_MAPPER);
	}

	@Override
	public List<UserExtSource> getUserExtSources(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.USER_EXT_SOURCE, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), USER_EXT_SOURCE_ROW_MAPPER);
	}

	@Override
	public List<Vo> getVos(List<String> attrsNames, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.entityQueryBuilder(PerunEntityType.VO, attrsNames, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), VO_ROW_MAPPER);
	}
}
