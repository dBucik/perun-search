package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.richEntities.RichUserExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface UserExtSourceDAO {

    void setDataSource(DataSource dataSource);

    List<UserExtSource> getUserExtSources(List<InputAttribute> core);

    List<RichUserExtSource> getRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichUserExtSource> getCompleteRichUserExtSources(List<InputAttribute> core, List<InputAttribute> attrs);

}
