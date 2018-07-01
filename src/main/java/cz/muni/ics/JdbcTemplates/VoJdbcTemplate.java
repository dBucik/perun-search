package cz.muni.ics.JdbcTemplates;

import cz.muni.ics.DAOs.VoDAO;
import cz.muni.ics.mappers.AttributeMapper;
import cz.muni.ics.mappers.VoMapper;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Vo;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VoJdbcTemplate implements VoDAO {

    private static final VoMapper MAPPER = new VoMapper();
    private static final AttributeMapper ATTR_MAPPER = new AttributeMapper();

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
        List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {id}, ATTR_MAPPER);
        vo.setAttributes(attrs);

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

            List<Attribute> attrs = jdbcTemplate.query(attrQuery, new Object[] {vo.getId()}, ATTR_MAPPER);
            vo.setAttributes(attrs);
        }

        return vos;
    }

    public List<Vo> getVosByAttrs(List<InputAttribute> inputAttributes) {
        List<Vo> vos = getVos();
        if (vos.isEmpty()) {
            return null;
        }

        List<Attribute> attrs = convertAttrsFromInput(inputAttributes);
        vos.removeIf(vo -> {
            assert attrs != null;
            return !vo.getAttributes().containsAll(attrs);
        });

        return vos;
    }

    public List<Attribute> getVoWithAttrs(Long id, List<String> attrNames) {
        Vo vo = getVo(id);
        if (vo == null) {
            return null;
        }

        vo.getAttributes().removeIf(attribute -> !attrNames.contains(attribute.getKey()));
        return vo.getAttributes();
    }

    private List<Attribute> convertAttrsFromInput(List<InputAttribute> input) {
        List<Attribute> result = new ArrayList<>();
        for (InputAttribute inputAttribute: input) {
            Attribute filter = new Attribute(inputAttribute.getKey(), inputAttribute.getValue());
            result.add(filter);
        }

        if (result.isEmpty()) {
            return null;
        }

        return result;
    }
}
