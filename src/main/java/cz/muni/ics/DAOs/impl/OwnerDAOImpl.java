package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.DAOs.OwnerDAO;
import cz.muni.ics.mappers.entities.OwnerMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
public class OwnerDAOImpl implements OwnerDAO {

	private static final Logger log = LoggerFactory.getLogger(OwnerDAOImpl.class);

	private static final OwnerMapper MAPPER = new OwnerMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Owner> getOwners(List<InputAttribute> core) {
		JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.OWNER, core);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}
	
}
