package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.OwnerDAO;
import cz.muni.ics.mappers.entities.OwnerMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Owner;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS;
import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;
public class OwnerDAOImpl implements OwnerDAO {

	private static final OwnerMapper MAPPER = new OwnerMapper();

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Owner> getOwners(List<InputAttribute> core) {
		String where = DAOUtils.outerWhereBuilder(core, null);
		String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.OWNER);
		Object[] params = DAOUtils.buildParams(NO_ATTRS_NAMES, core, NO_ATTRS);

		return jdbcTemplate.query(query, params, MAPPER);
	}
	
}
