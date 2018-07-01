package cz.muni.ics.DAOs;

import cz.muni.ics.models.Group;

import javax.sql.DataSource;
import java.util.List;

public interface GroupDAO {

    void setDataSource(DataSource dataSource);

    Group getGroup(Long id);

    List<Group> getGroups();
}
