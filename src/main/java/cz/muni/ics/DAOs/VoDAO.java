package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Vo;

import javax.sql.DataSource;
import java.util.List;

public interface VoDAO {

    void setDataSource(DataSource ds);

    /**
     * Get VO specified by ID.
     * @param id id of VO
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found VO or null if not such found
     * @throws DatabaseIntegrityException More than one VO with same ID found.
     */
    Vo getVo(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get VO with names like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name substring in name of VO
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of VOs, empty list if nothing has been found.
     */
    List<Vo> getVosByName(String name, boolean withAttrs);

    /**
     * Get VO specified by SHORT NAME.
     * (EXACT matching used, comparing ignores case)
     * @param shortName short name of VO
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found VO or null if not such found.
     * @throws DatabaseIntegrityException More than one VO with same shortName found.
     */
    Vo getVoByShortName(String shortName, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get VOs with short names like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param shortName substring in short_name of vo
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of vos, empty list if nothing has been found.
     */
    List<Vo> getVosByShortName(String shortName, boolean withAttrs);

    /**
     * Get all VOs.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of VOs, empty list if nothing has been found.
     */
    List<Vo> getVos(boolean withAttrs);

    /**
     * Get attributes of VO specified by ID. Only attributes with value are returned.
     * @param id id of VO
     * @param attrs attributes to be fetched, null for all attributes with value
     * @return List of attributes, empty list if nothing has been found.
     * @throws DatabaseIntegrityException More than one VO with same ID found.
     */
    List<Attribute> getVoAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get VOs that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of VOs
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of VOs found, empty list if nothing has been found.
     */
    List<Vo> getVosWithAttrs(List<InputAttribute> attrs, boolean withAttrs);
}
