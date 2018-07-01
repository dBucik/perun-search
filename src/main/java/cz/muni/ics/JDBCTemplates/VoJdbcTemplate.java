package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.VoDAO;
import cz.muni.ics.mappers.AttributesToJsonMapper;
import cz.muni.ics.mappers.VoMapper;
import cz.muni.ics.models.AttributePair;
import cz.muni.ics.models.Vo;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoJdbcTemplate implements VoDAO {

    private static final VoMapper MAPPER = new VoMapper();
    private static final AttributesToJsonMapper ATTR_MAPPER = new AttributesToJsonMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vo getVo(Long id) {
        //TODO better query
        String query = "SELECT * FROM vos WHERE id=?";
        Vo vo = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);

        String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                "FROM attr_names an RIGHT OUTER JOIN vo_attr_values av " +
                "ON (an.id = av.attr_id) " +
                "WHERE av.vo_id=?";
        List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        Map<String, String> attrMap = new HashMap<>();
        for (AttributePair attr: attrs) {
            attrMap.put(attr.getAttrName(), attr.getAttrValue());
        }
        vo.setAttributes(new JSONObject(attrMap));

        return vo;

    }

    @Override
    public List<Vo> getVos() {
        //TODO better query
        String query = "SELECT * FROM vos";
        List<Vo> vos = jdbcTemplate.query(query, MAPPER);

        for (Vo vo: vos) {
            String attrQuery = "SELECT an.friendly_name, av.attr_value " +
                    "FROM attr_names an RIGHT OUTER JOIN vo_attr_values av " +
                    "ON (an.id = av.attr_id) " +
                    "WHERE av.vo_id=?";

            List<AttributePair> attrs = jdbcTemplate.query(attrQuery, new Object[] {vo.getId()}, ATTR_MAPPER);
            Map<String, String> attrMap = new HashMap<>();
            for (AttributePair attr: attrs) {
                attrMap.put(attr.getAttrName(), attr.getAttrValue());
            }
            vo.setAttributes(new JSONObject(attrMap));
        }

        return vos;
    }
}
