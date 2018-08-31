package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.mappers.entities.FacilityMapper;
import cz.muni.ics.mappers.richEntities.RichFacilityMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.richEntities.RichFacility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;

public class FacilityDAOImpl implements FacilityDAO {

	private static final Logger log = LoggerFactory.getLogger(FacilityDAOImpl.class);

	private static final FacilityMapper MAPPER = new FacilityMapper();
	private static final RichFacilityMapper RICH_MAPPER = new RichFacilityMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Facility> getFacilities(List<InputAttribute> core) {
		JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.FACILITY, core);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<RichFacility> getRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs,
								   List<String> attrsNames) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.FACILITY, attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

	@Override
	public List<RichFacility> getCompleteRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs) {
		JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.FACILITY, NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
	}

}
