package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Vo;

import javax.sql.DataSource;
import java.util.List;

public interface VoDAO {

    void setDataSource(DataSource ds);

    /**
     * Get vo specified by ID.
     * @param id id of vo
     * @return Found vo or null if not such found.
     */
    Vo getVo(Long id);

    /**
     * Get vo specified by NAME.
     * @param name name of vo
     * @return Found vo or null if not such found.
     */
    Vo getVoByName(String name);

    /**
     * Get vo specified by SHORT NAME.
     * @param shortName name of vo
     * @return Found vo or null if not such found.
     */
    Vo getVoByShortName(String shortName);

    /**
     * Get all vos.
     * @return List of vos, null if nothing has been found.
     */
    List<Vo> getVos();

    /**
     * Get attributes of vo specified by ID.
     * @param id id of vo
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getVoAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get vos that have specified attributes. (Exact matching used)
     * @param attrs attributes of vos
     * @return List of vos found, empty list if nothing has been found.
     */
    List<Vo> getVosWithAttrs(List<InputAttribute> attrs);
}
