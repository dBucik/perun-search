package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.richEntities.RichService;

import javax.sql.DataSource;
import java.util.List;

public interface ServiceDAO {

    void setDataSource(DataSource dataSource);

    List<Service> getServices(List<InputAttribute> core);

    List<RichService> getRichServices(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichService> getCompleteRichServices(List<InputAttribute> core, List<InputAttribute> attrs);

}
