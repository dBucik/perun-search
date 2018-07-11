package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.VoDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.VoMapper;
import cz.muni.ics.mappers.richEntities.RichVoMapper;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.PerunEntityType;
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

    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vo getVo(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.VO);
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
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.VO);

        return jdbcTemplate.query(query, new Object[] { name }, MAPPER);
    }

    @Override
    public Vo getVoByShortName(String shortName) throws DatabaseIntegrityException {
        String where = "WHERE upper(t.short_name) = upper(?)";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.VO);

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
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.VO);

        return jdbcTemplate.query(query, new Object[] { shortName }, MAPPER);
    }

    @Override
    public List<Vo> getVos() {
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.VO);
        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Vo> getVosHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getCompleteRichVosHavingAttrs(attrs));
    }

    /* COMPLETE_RICH_VO */

    @Override
    public RichVo getCompleteRichVo(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.VO);
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{ id }, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichVo> getCompleteRichVosByName(String name) {
        name = '%' + name + '%';
        String entityWhere = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.VO);

        return jdbcTemplate.query(query, new Object[] { name }, RICH_MAPPER);
    }

    @Override
    public RichVo getCompleteRichVoByShortName(String shortName) throws DatabaseIntegrityException {
        String entityWhere = "WHERE upper(t.short_name) = upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.VO);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{ shortName }, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More VOs with same shortName found [shortName: " + shortName + ']');
        }
    }

    @Override
    public List<RichVo> getCompleteRichVosByShortName(String shortName) {
        String entityWhere = "WHERE upper(t.short_name) = upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.VO);

        return jdbcTemplate.query(query, new Object[]{ shortName }, RICH_MAPPER);
    }

    @Override
    public List<RichVo> getCompleteRichVos() {
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.VO);
        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichVo> getCompleteRichVosHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichVo> all = getCompleteRichVos();
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
        RichVo vo = getCompleteRichVo(id);
        return new ArrayList<>(vo.getAttributesByKeys(attrs));
    }

}
