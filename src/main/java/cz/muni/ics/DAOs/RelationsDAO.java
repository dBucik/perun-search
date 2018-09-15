package cz.muni.ics.DAOs;

import cz.muni.ics.models.attributes.InputAttribute;
import cz.muni.ics.models.Relation;

import javax.sql.DataSource;
import java.util.List;

public interface RelationsDAO {

    void setDataSource(DataSource dataSource);

    List<Relation> getRelations(String relType, Long primaryId, Long secondaryId);

    List<Relation> getRichRelations(String relType, Long primaryId, Long secondaryId, List<String> attrsNames,
                                    List<InputAttribute> attrs);

    List<Relation> getCompleteRichRelations(String relType, Long primaryId, Long secondaryId, List<InputAttribute> attrs);

}
