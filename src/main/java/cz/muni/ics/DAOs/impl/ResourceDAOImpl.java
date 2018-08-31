package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.DAOs.ResourceDAO;
import cz.muni.ics.mappers.entities.ResourceMapper;
import cz.muni.ics.mappers.richEntities.RichResourceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.richEntities.RichResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;

public class ResourceDAOImpl implements ResourceDAO {

	private static final Logger log = LoggerFactory.getLogger(ResourceDAOImpl.class);

	private static final ResourceMapper MAPPER = new ResourceMapper();
	private static final RichResourceMapper RICH_MAPPER = new RichResourceMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Resource> getResources(List<InputAttribute> core) {
		JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.RESOURCE, core);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<RichResource> getRichResources(List<InputAttribute> core, List<InputAttribute> attrs,
								   List<String> attrsNames) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.RESOURCE, attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

	@Override
	public List<RichResource> getCompleteRichResources(List<InputAttribute> core, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.RESOURCE, NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

}
