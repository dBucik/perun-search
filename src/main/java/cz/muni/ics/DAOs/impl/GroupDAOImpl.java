package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.GroupDAO;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.mappers.entities.GroupMapper;
import cz.muni.ics.mappers.richEntities.RichGroupMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.richEntities.RichGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS;
import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;
import static cz.muni.ics.DAOs.DAOUtils.NO_WHERE;

public class GroupDAOImpl implements GroupDAO {

	private static final Logger log = LoggerFactory.getLogger(GroupDAOImpl.class);

	private static final GroupMapper MAPPER = new GroupMapper();
	private static final RichGroupMapper RICH_MAPPER = new RichGroupMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Group> getGroups(List<InputAttribute> core) {
		JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.GROUP, core);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<RichGroup> getRichGroups(List<InputAttribute> core, List<InputAttribute> attrs,
												 List<String> attrsNames) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.VO, attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

	@Override
	public List<RichGroup> getCompleteRichGroups(List<InputAttribute> core, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.VO, NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}
	
}
