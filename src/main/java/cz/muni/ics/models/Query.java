package cz.muni.ics.models;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import cz.muni.ics.JDBCTemplates.VoJdbcTemplate;

import java.util.List;

public class Query implements GraphQLRootResolver {

    private final VoJdbcTemplate voJdbcTemplate;

    public Query(VoJdbcTemplate voJdbcTemplate) {
        this.voJdbcTemplate = voJdbcTemplate;
    }

    public List<Vo> allVos() {
        return voJdbcTemplate.getVos();
    }
}
