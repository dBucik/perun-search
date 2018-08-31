
package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.ExtSourceDAO;
import cz.muni.ics.DAOs.JDBCQuery;
import cz.muni.ics.mappers.entities.ExtSourceMapper;
import cz.muni.ics.mappers.richEntities.RichExtSourceMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.richEntities.RichExtSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

import static cz.muni.ics.DAOs.DAOUtils.NO_ATTRS_NAMES;

public class ExtSourceDAOImpl implements ExtSourceDAO {

    private static final Logger log = LoggerFactory.getLogger(ExtSourceDAOImpl.class);

    private static final ExtSourceMapper MAPPER = new ExtSourceMapper();
    private static final RichExtSourceMapper RICH_MAPPER = new RichExtSourceMapper();

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<ExtSource> getExtSources(List<InputAttribute> core) {
        JDBCQuery query = DAOUtils.simpleQueryBuilder(PerunEntityType.EXT_SOURCE, core);

        log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
        return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), MAPPER);
    }

    @Override
    public List<RichExtSource> getRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
                                   List<String> attrsNames) {
        JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.EXT_SOURCE, attrsNames, core, attrs);

        log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
        return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
    }

    @Override
    public List<RichExtSource> getCompleteRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs) {
        JDBCQuery query = DAOUtils.complexQueryBuilder(PerunEntityType.EXT_SOURCE, NO_ATTRS_NAMES, core, attrs);

        log.info("Executing query: {}, with params: {}", query.getQueryString(), query.getParamsMap());
        return jdbcTemplate.query(query.getQueryString(), query.getParamsMap(), RICH_MAPPER);
    }

}
