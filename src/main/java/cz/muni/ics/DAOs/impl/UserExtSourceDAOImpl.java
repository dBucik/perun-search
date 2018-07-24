package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.mappers.entities.UserExtSourceMapper;
import cz.muni.ics.mappers.richEntities.RichUserExtSourceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.richEntities.RichUserExtSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS;
import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;
import static cz.muni.ics.DAOs.DAOUtils.NO_WHERE;

public class UserExtSourceDAOImpl implements UserExtSourceDAO {

	private static final Logger log = LoggerFactory.getLogger(UserExtSourceDAOImpl.class);

	private static final UserExtSourceMapper MAPPER = new UserExtSourceMapper();
	private static final RichUserExtSourceMapper RICH_MAPPER = new RichUserExtSourceMapper();

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<UserExtSource> getUserExtSources(List<InputAttribute> core) {
		String where = DAOUtils.outerWhereBuilder(core, null);
		String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.USER_EXT_SOURCE);
		Object[] params = DAOUtils.buildEntityParams(NO_ATTRS_NAMES, core, NO_ATTRS);

		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, MAPPER);
	}

	@Override
	public List<RichUserExtSource> getRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
												 List<String> attrsNames) {
		int size = attrs == null ? 0 : attrs.size();
		size += attrsNames == null ? 0 : attrsNames.size();
		String innerWhere = DAOUtils.innerWhereBuilder(size);
		String outerWhere = DAOUtils.outerWhereBuilder(core, attrs);
		String query = DAOUtils.complexQueryBuilder(innerWhere, outerWhere, PerunEntityType.USER_EXT_SOURCE);
		Object[] params = DAOUtils.buildEntityParams(attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, RICH_MAPPER);
	}

	@Override
	public List<RichUserExtSource> getCompleteRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
		String outerWhere = DAOUtils.outerWhereBuilder(core, attrs);
		String query = DAOUtils.complexQueryBuilder(NO_WHERE, outerWhere, PerunEntityType.USER_EXT_SOURCE);
		Object[] params = DAOUtils.buildEntityParams(NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, RICH_MAPPER);
	}
	
}
