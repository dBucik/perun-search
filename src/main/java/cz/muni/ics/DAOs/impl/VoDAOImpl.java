package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.VoDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.VoMapper;
import cz.muni.ics.mappers.richEntities.RichVoMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Vo;
import cz.muni.ics.models.richEntities.RichVo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class VoDAOImpl implements VoDAO {

    private static final VoMapper MAPPER = new VoMapper();
    private static final RichVoMapper RICH_MAPPER = new RichVoMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vo getVo(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, false);
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Vo> getVosByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] { name }, MAPPER);
    }

    @Override
    public Vo getVoByShortName(String shortName) throws DatabaseIntegrityException {
        String where = "WHERE upper(t.short_name) = upper(?)";
        String query = queryBuilder(where, false);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{ shortName }, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same shortName found [shortName: " + shortName + ']');
        }
    }

    @Override
    public List<Vo> getVosByShortName(String shortName) {
        shortName = '%' + shortName + '%';
        String where = "WHERE upper(t.short_name) LIKE upper(?)";
        String query = queryBuilder(where, false);

        return jdbcTemplate.query(query, new Object[] { shortName }, MAPPER);
    }

    @Override
    public List<Vo> getVos() {
        String query = queryBuilder(null, false);
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Vo> getVosHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getRichVosHavingAttrs(attrs));
    }

    /* RICH_VO */

    @Override
    public RichVo getRichVo(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = queryBuilder(where, true);
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{ id }, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichVo> getRichVosByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[] { name }, RICH_MAPPER);
    }

    @Override
    public RichVo getRichVoByShortName(String shortName) throws DatabaseIntegrityException {
        String where = "WHERE upper(t.short_name) = upper(?)";
        String query = queryBuilder(where, true);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{ shortName }, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same shortName found [shortName: " + shortName + ']');
        }
    }

    @Override
    public List<RichVo> getRichVosByShortName(String shortName) {
        String where = "WHERE upper(t.short_name) = upper(?)";
        String query = queryBuilder(where, true);

        return jdbcTemplate.query(query, new Object[]{ shortName }, RICH_MAPPER);
    }

    @Override
    public List<RichVo> getRichVos() {
        String query = queryBuilder(null, true);
        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichVo> getRichVosHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichVo> all = getRichVos();
        List<RichVo> correct = new ArrayList<>();
        for (RichVo vo: all) {
            if (DAOUtils.hasAttributes(vo, attrs)) {
                correct.add(vo);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichVo vo = getRichVo(id);
        return new ArrayList<>(vo.getAttributesByKeys(attrs));
    }

    private String queryBuilder(String where, boolean withAttrs) {
        //TODO: check table names
        StringBuilder query = new StringBuilder();
        query.append("SELECT to_jsonb(t)");
        if (withAttrs) {
            query.append(" ||");
            query.append(" jsonb_build_object('attributes', json_agg(jsonb_build_object('key', friendly_name," +
                    " 'val', attr_value, 'val_text', attr_value_text, 'type', type)))");
        }
        query.append(" AS vo");
        query.append(" FROM vos t");
        if (withAttrs) {
            query.append(" JOIN vo_attr_values av ON av.vo_id = t.id");
            query.append(" JOIN attr_names an ON an.id = av.attr_id");
        }
        if (where != null) {
            query.append(' ').append(where.trim());
        }
        query.append(" GROUP BY t.id");
        return query.toString();
    }

}
