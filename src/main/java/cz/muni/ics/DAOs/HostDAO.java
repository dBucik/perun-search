package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.richEntities.RichHost;

import javax.sql.DataSource;
import java.util.List;

public interface HostDAO {

    void setDataSource(DataSource dataSource);

    List<Host> getHosts(List<InputAttribute> core);

    List<RichHost> getRichHosts(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichHost> getCompleteRichHosts(List<InputAttribute> core, List<InputAttribute> attrs);

}
