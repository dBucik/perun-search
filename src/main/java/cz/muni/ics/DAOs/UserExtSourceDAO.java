package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.richEntities.RichUserExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface UserExtSourceDAO {

    void setDataSource(DataSource dataSource);

    /* USER_EXT_SOURCE */

    /**
     * Get userExtSource specified by ID.
     * @param id id of userExtSource
     * @return Found userExtSource or null if not such found.
     */
    UserExtSource getUserExtSource(Long id) throws DatabaseIntegrityException;

    /**
     * Get all userExtSources.
     * @return List of userExtSources, null if nothing has been found.
     */
    List<UserExtSource> getUserExtSources();

    /**
     * Get userExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of userExtSources
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesWithAttrs(List<InputAttribute> attrs);

    /**
     * Get userExtSources of user specified by ID.
     * @param userId id of user
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesOfUser(Long userId);

    /**
     * Get userExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesOfExtSource(Long extSourceId);

    /**
     * Get userExtSources with specified loginExt
     * @param loginExt loginExt of userExtSource
     * @return List of userExtSources found, empty list if nothing has been found.
     */
    List<UserExtSource> getUserExtSourcesByLoginExt(String loginExt);

    /* RICH_USER_EXT_SOURCE */

    /**
     * Get richUserExtSource specified by ID.
     * @param id id of richUserExtSource
     * @return Found richUserExtSource or null if not such found.
     */
    RichUserExtSource getRichUserExtSource(Long id) throws DatabaseIntegrityException;

    /**
     * Get all richUserExtSources.
     * @return List of richUserExtSources, null if nothing has been found.
     */
    List<RichUserExtSource> getRichUserExtSources();

    /**
     * Get richUserExtSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of richUserExtSources
     * @return List of richUserExtSources found, empty list if nothing has been found.
     */
    List<RichUserExtSource> getRichUserExtSourcesWithAttrs(List<InputAttribute> attrs);

    /**
     * Get richUserExtSources of user specified by ID.
     * @param userId id of user
     * @return List of richUserExtSources found, empty list if nothing has been found.
     */
    List<RichUserExtSource> getRichUserExtSourcesOfUser(Long userId);

    /**
     * Get richUserExtSources of extSource specified by ID.
     * @param extSourceId id of extSource
     * @return List of richUserExtSources found, empty list if nothing has been found.
     */
    List<RichUserExtSource> getRichUserExtSourcesOfExtSource(Long extSourceId);

    /**
     * Get richUserExtSources with specified loginExt
     * @param loginExt loginExt of richUserExtSource
     * @return List of richUserExtSources found, empty list if nothing has been found.
     */
    List<RichUserExtSource> getRichUserExtSourcesByLoginExt(String loginExt);

    /* ATTRIBUTES */

    /**
     * Get attributes of userExtSource specified by ID.
     * @param id id of userExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getUserExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

}
