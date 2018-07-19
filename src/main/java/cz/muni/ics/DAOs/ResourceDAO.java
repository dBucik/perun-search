package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.richEntities.RichResource;

import javax.sql.DataSource;
import java.util.List;

public interface ResourceDAO {

    void setDataSource(DataSource dataSource);

    List<Resource> getResources(List<InputAttribute> core);

    List<RichResource> getRichResources(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichResource> getCompleteRichResources(List<InputAttribute> core, List<InputAttribute> attrs);

}
