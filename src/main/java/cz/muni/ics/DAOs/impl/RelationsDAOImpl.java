package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.RelationsDAO;
import cz.muni.ics.mappers.RelationMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Relation;
import cz.muni.ics.models.RelationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;
import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES_COUNT;

public class RelationsDAOImpl implements RelationsDAO {

	private static final Logger log = LoggerFactory.getLogger(RelationsDAOImpl.class);

	private static final RelationMapper MAPPER = new RelationMapper();

	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Relation> getRelations(String relType, InputAttribute primary, InputAttribute secondary) {
		RelationType type = Relation.resolveType(relType);
		String where = DAOUtils.relationWhereBuilder(type, primary, secondary, DAOUtils.NO_ATTRS_NAMES_COUNT,
				DAOUtils.NO_ATTRS);
		String query = DAOUtils.relationQueryBuilder(where, type);

		Object[] params = DAOUtils.buildRelationParams(relType, primary, secondary, NO_ATTRS_NAMES, DAOUtils.NO_ATTRS);
		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, MAPPER);
	}

	@Override
	public List<Relation> getRichRelations(String relType, InputAttribute primary, InputAttribute secondary,
										   List<String> attrsNames, List<InputAttribute> attrs) {
		RelationType type = Relation.resolveType(relType);
		int attrsNamesCount = attrsNames == null ? NO_ATTRS_NAMES_COUNT : attrsNames.size();
		String where = DAOUtils.relationWhereBuilder(type, primary, secondary, attrsNamesCount, attrs);
		String query = DAOUtils.relationQueryBuilder(where, type);

		Object[] params = DAOUtils.buildRelationParams(relType, primary, secondary, attrsNames, attrs);
		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, MAPPER);
	}

	@Override
	public List<Relation> getCompleteRichRelations(String relType, InputAttribute primary, InputAttribute secondary,
												   List<InputAttribute> attrs) {
		RelationType type = Relation.resolveType(relType);
		String where = DAOUtils.relationWhereBuilder(type, primary, secondary, NO_ATTRS_NAMES_COUNT, attrs);
		String query = DAOUtils.relationQueryBuilder(where, type);

		Object[] params = DAOUtils.buildRelationParams(relType, primary, secondary, NO_ATTRS_NAMES, attrs);
		log.info("Executing query: {}, with params: {}", query, params);
		return jdbcTemplate.query(query, params, MAPPER);
	}

}
