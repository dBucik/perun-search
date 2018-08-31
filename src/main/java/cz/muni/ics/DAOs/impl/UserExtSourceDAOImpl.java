package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.DAOs.UserExtSourceDAO;
import cz.muni.ics.mappers.entities.UserExtSourceMapper;
import cz.muni.ics.mappers.richEntities.RichUserExtSourceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.richEntities.RichUserExtSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;

public class UserExtSourceDAOImpl implements UserExtSourceDAO {

	private static final Logger log = LoggerFactory.getLogger(UserExtSourceDAOImpl.class);

	private static final UserExtSourceMapper MAPPER = new UserExtSourceMapper();
	private static final RichUserExtSourceMapper RICH_MAPPER = new RichUserExtSourceMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<UserExtSource> getUserExtSources(List<InputAttribute> core) {
		JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.USER_EXT_SOURCE, core);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<RichUserExtSource> getRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
								   List<String> attrsNames) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.USER_EXT_SOURCE, attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

	@Override
	public List<RichUserExtSource> getCompleteRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.USER_EXT_SOURCE, NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

}
