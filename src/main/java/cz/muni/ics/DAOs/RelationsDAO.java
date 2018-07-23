package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Relation;

import javax.sql.DataSource;
import java.util.List;

public interface RelationsDAO {

    void setDataSource(DataSource dataSource);

    List<Relation> getRelations(String relType, List<InputAttribute> core);

    List<Relation> getRichRelations(String relType,List<InputAttribute> core,
                                    List<InputAttribute> attrs, List<String> attrsNames);

    List<Relation> getCompleteRichRelations(String relType, List<InputAttribute> core, List<InputAttribute> attrs);

}
