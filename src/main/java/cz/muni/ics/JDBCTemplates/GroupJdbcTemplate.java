package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.GroupDAO;
import cz.muni.ics.mappers.GroupMapper;
import cz.muni.ics.models.Group;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class GroupJdbcTemplate implements GroupDAO {

    private static final GroupMapper MAPPER = new GroupMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Group getGroup(Long id) {
        //TODO query
        String query = "";
        Group group = jdbcTemplate.queryForObject(query, new Object[] {id}, MAPPER);
        return group;
    }

    @Override
    public List<Group> getGroups() {
        //TODO query;
        String query = "";
        List<Group> groups = jdbcTemplate.query(query, new Object[] {}, MAPPER);
        return groups;
    }
}
