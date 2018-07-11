package cz.muni.ics.DAOs.impl;

import cz.muni.ics.DAOs.DAOUtils;
import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.mappers.entities.FacilityMapper;
import cz.muni.ics.mappers.richEntities.RichFacilityMapper;
import cz.muni.ics.models.PerunEntityType;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichFacility;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FacilityDAOImpl implements FacilityDAO {

    private static final FacilityMapper MAPPER = new FacilityMapper();
    private static final RichFacilityMapper RICH_MAPPER = new RichFacilityMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* FACILITY */

    @Override
    public Facility getFacility(Long id) throws DatabaseIntegrityException {
        String where = "WHERE t.id = ?";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.FACILITY);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More facilities with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<Facility> getFacilities() {
        String query = DAOUtils.simpleQueryBuilder(null, PerunEntityType.FACILITY);

        return jdbcTemplate.query(query, MAPPER);
    }

    @Override
    public List<Facility> getFacilitiesHavingAttrs(List<InputAttribute> attrs) {
        return new ArrayList<>(getCompleteRichFacilitiesHavingAttrs(attrs));
    }

    @Override
    public List<Facility> getFacilitiesByName(String name) {
        name = '%' + name + '%';
        String where = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.simpleQueryBuilder(where, PerunEntityType.FACILITY);

        return jdbcTemplate.query(query, new Object[] {name}, MAPPER);
    }

    /* COMPLETE_RICH_FACILITY */

    @Override
    public RichFacility getCompleteRichFacility(Long id) throws DatabaseIntegrityException {
        String entityWhere = "WHERE t.id = ?";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.FACILITY);

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, RICH_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSetColumnCountException e) {
            throw new DatabaseIntegrityException("More facilities with same ID found [id: " + id + ']');
        }
    }

    @Override
    public List<RichFacility> getCompleteRichFacilities() {
        String query = DAOUtils.queryBuilder(null, null, PerunEntityType.FACILITY);

        return jdbcTemplate.query(query, RICH_MAPPER);
    }

    @Override
    public List<RichFacility> getCompleteRichFacilitiesByName(String name) {
        name = '%' + name + '%';
        String entityWhere = "WHERE upper(t.name) LIKE upper(?)";
        String query = DAOUtils.queryBuilder(entityWhere, null, PerunEntityType.FACILITY);

        return jdbcTemplate.query(query, new Object[] {name}, RICH_MAPPER);
    }

    @Override
    public List<RichFacility> getCompleteRichFacilitiesHavingAttrs(List<InputAttribute> attrs) {
        //TODO: improve
        List<RichFacility> all = getCompleteRichFacilities();
        List<RichFacility> correct = new ArrayList<>();
        for (RichFacility facility: all) {
            if (DAOUtils.hasAttributes(facility, attrs)) {
                correct.add(facility);
            }
        }

        return correct;
    }

    /* ATTRIBUTES */

    @Override
    public List<PerunAttribute> getFacilityAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException {
        //TODO: improve
        RichFacility facility = getCompleteRichFacility(id);
        return facility.getAttributesByKeys(attrs);
    }

}
