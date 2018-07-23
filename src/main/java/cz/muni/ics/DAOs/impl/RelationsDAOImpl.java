package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.RelationsDAO;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Relation;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class RelationsDAOImpl implements RelationsDAO {

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Relation> getRelations(String relType, List<InputAttribute> core) {
		return null;
	}

	@Override
	public List<Relation> getRichRelations(String relType, List<InputAttribute> core, List<InputAttribute> attrs, List<String> attrsNames) {
		return null;
	}

	@Override
	public List<Relation> getCompleteRichRelations(String relType, List<InputAttribute> core, List<InputAttribute> attrs) {
		return null;
	}

}
