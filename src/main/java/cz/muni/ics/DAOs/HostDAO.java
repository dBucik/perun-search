package cz.muni.ics.DAOs;

import cz.muni.ics.models.Host;

import javax.sql.DataSource;
import java.util.List;

public interface HostDAO {

    void setDataSource(DataSource dataSource);

    Host getHost(Long id);

    List<Host> getHosts();
}
