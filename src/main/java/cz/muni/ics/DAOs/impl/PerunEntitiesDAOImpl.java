package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.DAOs.PerunEntitiesDAO;
import cz.muni.ics.mappers.entities.ExtSourceMapper;
import cz.muni.ics.mappers.entities.FacilityMapper;
import cz.muni.ics.mappers.entities.GroupMapper;
import cz.muni.ics.mappers.entities.HostMapper;
import cz.muni.ics.mappers.entities.MemberMapper;
import cz.muni.ics.mappers.entities.OwnerMapper;
import cz.muni.ics.mappers.entities.ResourceMapper;
import cz.muni.ics.mappers.entities.ServiceMapper;
import cz.muni.ics.mappers.entities.UserExtSourceMapper;
import cz.muni.ics.mappers.entities.UserMapper;
import cz.muni.ics.mappers.entities.VoMapper;
import cz.muni.ics.models.attributes.InputAttribute;
import cz.muni.ics.models.enums.PerunEntityType;
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

public class PerunEntitiesDAOImpl implements PerunEntitiesDAO {

	private static final Logger log = LoggerFactory.getLogger(PerunEntitiesDAOImpl.class);

	private static final ExtSourceMapper EXT_SOURCE_MAPPER = new ExtSourceMapper();
	private static final FacilityMapper FACILITY_MAPPER = new FacilityMapper();
	private static final GroupMapper GROUP_MAPPER = new GroupMapper();
	private static final HostMapper HOST_MAPPER = new HostMapper();
	private static final MemberMapper MEMBER_MAPPER = new MemberMapper();
	private static final OwnerMapper OWNER_MAPPER = new OwnerMapper();
	private static final ResourceMapper RESOURCE_MAPPER = new ResourceMapper();
	private static final ServiceMapper SERVICE_MAPPER = new ServiceMapper();
	private static final UserMapper USER_MAPPER = new UserMapper();
	private static final UserExtSourceMapper USER_EXT_SOURCE_MAPPER = new UserExtSourceMapper();
	private static final VoMapper VO_MAPPER = new VoMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<ExtSource> getExtSources(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), EXT_SOURCE_MAPPER);
	}

	@Override
	public List<Facility> getFacilities(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), FACILITY_MAPPER);
	}

	@Override
	public List<Group> getGroups(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), GROUP_MAPPER);
	}

	@Override
	public List<Host> getHosts(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), HOST_MAPPER);
	}

	@Override
	public List<Member> getMembers(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MEMBER_MAPPER);
	}

	@Override
	public List<Owner> getOwners(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), OWNER_MAPPER);
	}

	@Override
	public List<Resource> getResources(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RESOURCE_MAPPER);
	}

	@Override
	public List<Service> getServices(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), SERVICE_MAPPER);
	}

	@Override
	public List<User> getUsers(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), USER_MAPPER);
	}

	@Override
	public List<UserExtSource> getUserExtSources(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), USER_EXT_SOURCE_MAPPER);
	}

	@Override
	public List<Vo> getVos(JDBCQuery query) {
		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), VO_MAPPER);
	}

}
