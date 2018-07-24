package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.mappers.entities.FacilityMapper;
import cz.muni.ics.mappers.richEntities.RichFacilityMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.richEntities.RichFacility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS;
import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;
import static cz.muni.ics.DAOs.DAOUtils.NO_WHERE;

public class FacilityDAOImpl implements FacilityDAO {

	private static final Logger log = LoggerFactory.getLogger(FacilityDAOImpl.class);

	private static final FacilityMapper MAPPER = new FacilityMapper();
	private static final RichFacilityMapper RICH_MAPPER = new RichFacilityMapper();

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Facility> getFacilities(List<InputAttribute> core) {
		String where = DAOUtils.outerWhereBuilder(core, null);
		String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.FACILITY);

		Object[] params = DAOUtils.buildEntityParams(NO_ATTRS_NAMES, core, NO_ATTRS);

		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, MAPPER);
	}

	@Override
	public List<RichFacility> getRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs,
												List<String> attrsNames) {
		int size = attrs == null ? 0 : attrs.size();
		size += attrsNames == null ? 0 : attrsNames.size();
		String innerWhere = DAOUtils.innerWhereBuilder(size);
		String outerWhere = DAOUtils.outerWhereBuilder(core, attrs);
		String query = DAOUtils.complexQueryBuilder(innerWhere, outerWhere, PerunEntityType.FACILITY);
		Object[] params = DAOUtils.buildEntityParams(attrsNames, core, attrs);

		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, RICH_MAPPER);
	}

	@Override
	public List<RichFacility> getCompleteRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs) {
		String outerWhere = DAOUtils.outerWhereBuilder(core, attrs);
		String query = DAOUtils.complexQueryBuilder(NO_WHERE, outerWhere, PerunEntityType.FACILITY);
		Object[] params = DAOUtils.buildEntityParams(NO_ATTRS_NAMES, core, attrs);

		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, RICH_MAPPER);
	}

}
