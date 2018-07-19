package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.richEntities.RichGroup;

import javax.sql.DataSource;
import java.util.List;

public interface GroupDAO {

    void setDataSource(DataSource dataSource);

    List<Group> getGroups(List<InputAttribute> core);

    List<RichGroup> getRichGroups(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichGroup> getCompleteRichGroups(List<InputAttribute> core, List<InputAttribute> attrs);

}
