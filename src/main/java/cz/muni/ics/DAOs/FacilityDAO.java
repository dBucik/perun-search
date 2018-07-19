package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.richEntities.RichFacility;

import javax.sql.DataSource;
import java.util.List;

public interface FacilityDAO {

    void setDataSource(DataSource dataSource);

    List<Facility> getFacilities(List<InputAttribute> core);

    List<RichFacility> getRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs,
										List<String> attrsNames);

    List<RichFacility> getCompleteRichFacilities(List<InputAttribute> core, List<InputAttribute> attrs);

}
