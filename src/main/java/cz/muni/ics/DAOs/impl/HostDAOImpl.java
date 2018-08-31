package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.HostDAO;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.mappers.entities.HostMapper;
import cz.muni.ics.mappers.richEntities.RichHostMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.richEntities.RichHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;

public class HostDAOImpl implements HostDAO {

	private static final Logger log = LoggerFactory.getLogger(HostDAOImpl.class);

	private static final HostMapper MAPPER = new HostMapper();
	private static final RichHostMapper RICH_MAPPER = new RichHostMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Host> getHosts(List<InputAttribute> core) {
		JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.HOST, core);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<RichHost> getRichHosts(List<InputAttribute> core, List<InputAttribute> attrs,
								   List<String> attrsNames) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.HOST, attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

	@Override
	public List<RichHost> getCompleteRichHosts(List<InputAttribute> core, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.HOST, NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

}
