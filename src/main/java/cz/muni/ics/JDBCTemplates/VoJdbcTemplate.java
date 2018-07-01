package cz.muni.ics.JDBCTemplates;

import cz.muni.ics.DAOs.VoDAO;
import cz.muni.ics.mappers.VoMapper;
import cz.muni.ics.models.Vo;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class VoJdbcTemplate implements VoDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Vo getVo(Long id) {
        //todo QUERY
        String query = "SELECT * FROM vos WHERE id=?";
        Vo vo = jdbcTemplate.queryForObject(query, new Object[] {id}, new VoMapper());
        return vo;

    }

    @Override
    public List<Vo> getVos() {
        String query = "SELECT * FROM vos";
        //todo QUERY
        List<Vo> vos = jdbcTemplate.query(query, new VoMapper());
        return vos;
    }
}
