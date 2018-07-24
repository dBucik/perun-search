package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Relation;

import javax.sql.DataSource;
import java.util.List;

public interface RelationsDAO {

    void setDataSource(DataSource dataSource);

    List<Relation> getRelations(String relType, InputAttribute primary, InputAttribute secondary);

    List<Relation> getRichRelations(String relType, InputAttribute primary, InputAttribute secondary,
                                    List<String> attrsNames, List<InputAttribute> attrs);

    List<Relation> getCompleteRichRelations(String relType, InputAttribute primary, InputAttribute secondary,
                                            List<InputAttribute> attrs);

}
