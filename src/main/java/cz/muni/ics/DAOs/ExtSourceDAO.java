package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.richEntities.RichExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface ExtSourceDAO {

    void setDataSource(DataSource dataSource);

    List<ExtSource> getExtSources(List<InputAttribute> core);

    List<RichExtSource> getRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs,
                                                 List<String> attrsNames);

    List<RichExtSource> getCompleteRichExtSources(List<InputAttribute> core, List<InputAttribute> attrs);
}
