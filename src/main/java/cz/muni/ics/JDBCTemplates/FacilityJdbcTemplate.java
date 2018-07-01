package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.FacilityDAO;
import cz.muni.ics.mappers.FacilityMapper;
import cz.muni.ics.models.Facility;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class FacilityJdbcTemplate implements FacilityDAO {

    private static final FacilityMapper MAPPER = new FacilityMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Facility getFacility(Long id) {
        //todo QUERY
        String query = "";
        Facility facility = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return facility;
    }

    @Override
    public List<Facility> getFacilities() {
        //todo QUERY
        String query = "";
        List<Facility> facilities = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return facilities;
    }
}
