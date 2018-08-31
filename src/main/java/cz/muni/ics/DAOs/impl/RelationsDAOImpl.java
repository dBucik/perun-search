package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.DAOs.RelationsDAO;
import cz.muni.ics.mappers.RelationMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Relation;
import cz.muni.ics.models.RelationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;

public class RelationsDAOImpl implements RelationsDAO {

	private static final Logger log = LoggerFactory.getLogger(RelationsDAOImpl.class);

	private static final RelationMapper MAPPER = new RelationMapper();

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Relation> getRelations(String relType, Long primaryId, Long secondaryId) {
		RelationType type = Relation.resolveType(relType);
		JDBCQuery query = DAOUtils.relationQueryBuilder(primaryId, secondaryId, NO_ATTRS_NAMES, DAOUtils.NO_ATTRS, type);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<Relation> getRichRelations(String relType, Long primaryId, Long secondaryId,
										   List<String> attrsNames, List<InputAttribute> attrs) {
		RelationType type = Relation.resolveType(relType);
		JDBCQuery query = DAOUtils.relationQueryBuilder(primaryId, secondaryId, attrsNames, attrs, type);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

	@Override
	public List<Relation> getCompleteRichRelations(String relType, Long primaryId, Long secondaryId,
												   List<InputAttribute> attrs) {
		RelationType type = Relation.resolveType(relType);
		JDBCQuery query = DAOUtils.relationQueryBuilder(primaryId, secondaryId, NO_ATTRS_NAMES, attrs, type);

		log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
		return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
	}

}
