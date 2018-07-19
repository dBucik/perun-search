package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.HostDAO;
import cz.muni.ics.mappers.entities.HostMapper;
import cz.muni.ics.mappers.richEntities.RichHostMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.richEntities.RichHost;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS;
import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;
import static cz.muni.ics.DAOs.DAOUtils.NO_WHERE;

public class HostDAOImpl implements HostDAO {

	private static final HostMapper MAPPER = new HostMapper();
	private static final RichHostMapper RICH_MAPPER = new RichHostMapper();

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Host> getHosts(List<InputAttribute> core) {
		String where = DAOUtils.outerWhereBuilder(core, null);
		String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.HOST);
		Object[] params = DAOUtils.buildParams(NO_ATTRS_NAMES, core, NO_ATTRS);

		return jdbcTemplate.query(query, params, MAPPER);
	}

	@Override
	public List<RichHost> getRichHosts(List<InputAttribute> core, List<InputAttribute> attrs,
												 List<String> attrsNames) {
		int size = attrs == null ? 0 : attrs.size();
		size += attrsNames == null ? 0 : attrsNames.size();
		String innerWhere = DAOUtils.innerWhereBuilder(size);
		String outerWhere = DAOUtils.outerWhereBuilder(core, attrs);
		String query = DAOUtils.complexQueryBuilder(innerWhere, outerWhere, PerunEntityType.HOST);
		Object[] params = DAOUtils.buildParams(attrsNames, core, attrs);

		return jdbcTemplate.query(query, params, RICH_MAPPER);
	}

	@Override
	public List<RichHost> getCompleteRichHosts(List<InputAttribute> core, List<InputAttribute> attrs) {
		String outerWhere = DAOUtils.outerWhereBuilder(core, attrs);
		String query = DAOUtils.complexQueryBuilder(NO_WHERE, outerWhere, PerunEntityType.HOST);
		Object[] params = DAOUtils.buildParams(NO_ATTRS_NAMES, core, attrs);

		return jdbcTemplate.query(query, params, RICH_MAPPER);
	}
	
}
