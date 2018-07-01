package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.ExtSourceDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.ExtSourceMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.ExtSource;
import cz.muni.ics.models.InputAttribute;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtSourceJdbcTemplate implements ExtSourceDAO {

    private static final ExtSourceMapper MAPPER = new ExtSourceMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ExtSource getExtSource(Long id) {
        //TODO better query
        String query = "SELECT * FROM ext_sources WHERE id=?";
        ExtSource extSource = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN ext_sources_attributes av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.ext_sources_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        extSource.setAttributes(attrs);

        return extSource;
    }

    @Override
    public ExtSource getExtSourceByName(String name) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<ExtSource> getExtSources() {
        //TODO better query
        String query = "SELECT * FROM ext_sources";
        List<ExtSource> extSources = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (ExtSource extSource: extSources) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN ext_sources_attributes av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.ext_sources_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {extSource.getId()}, ATTR_MAPPER);
            extSource.setAttributes(attrs);
        }
        return extSources;
    }

    @Override
    public ExtSource getExtSourceAttrs(Long id, List<InputAttribute> attrs) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<ExtSource> getExtSourcesByAttrs(List<InputAttribute> attrs) {
        //TODO implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
