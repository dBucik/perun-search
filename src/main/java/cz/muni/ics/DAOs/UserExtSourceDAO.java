package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.UserExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface UserExtSourceDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get userExtSource specified by ID.
     * @param id id of userExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found userExtSource or null if not such found.
     */
    UserExtSource getUserExtSource(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get all userExtSources.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources, null if nothing has been found.
     */
    List<UserExtSource> getUserExtSources(boolean withAttrs);

    /**
     * Get attributes of userExtSource specified by ID.
     * @param id id of userExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesWithAttrs(List<InputAttribute> attrs, boolean withAttrs);

    /**
     * Get userExtSources of user specified by ID.
     * @param userId id of user
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesOfUser(Long userId, boolean withAttrs);

    /**
     * Get userExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId, boolean withAttrs);

    /**
     * Get userExtSources with specified loginExt
     * @param loginExt loginExt of userExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt, boolean withAttrs);

}
