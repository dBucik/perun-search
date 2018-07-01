package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.FacilityMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Facility;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacilityJdbcTemplate implements FacilityDAO {

    private static final FacilityMapper MAPPER = new FacilityMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Facility getFacility(Long id) {
        //TODO better query
        String query = "SELECT * FROM facilities WHERE id=?";
        Facility facility = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN facility_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.facility_id=?";
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        Map<String, String> attrMap = new HashMap<>();
        for (Attribute attr: attrs) {
            attrMap.put(attr.getAttrName(), attr.getAttrValue());
        }
        facility.setAttributes(new JSONObject(attrMap));

        return facility;
    }

    @Override
    public List<Facility> getFacilities() {
        //TODO better query
        String query = "SELECT * FROM facilities";
        List<Facility> facilities = jdbcTemplate.query(query, new Object[] {}, MAPPER);

        for (Facility facility: facilities) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN facility_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.facility_id=?";
            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {facility.getId()}, ATTR_MAPPER);
            Map<String, String> attrMap = new HashMap<>();
            for (Attribute attr: attrs) {
                attrMap.put(attr.getAttrName(), attr.getAttrValue());
            }
            facility.setAttributes(new JSONObject(attrMap));
        }

        return facilities;
    }
}
