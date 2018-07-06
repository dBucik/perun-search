package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.richEntities.RichExtSource;

import javax.sql.DataSource;
import java.util.List;

public interface ExtSourceDAO {

    void setDataSource(DataSource dataSource);

    /* EXT_SOURCE */

    /**
     * Get ExtSource specified by ID.
     * @param id id of extSource
     * @return Found extSource or null if not such found.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    ExtSource getExtSource(Long id) throws DatabaseIntegrityException;

    /**
     * Get all ExtSources.
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSources();

    /**
     * Get ExtSources with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of ExtSource
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesByName(String name);

    /**
     * Get ExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of ExtSource
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesByType(String type);

    /**
     * Get ExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of ExtSources
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesHavingAttrs(List<InputAttribute> attrs);

    /* RICH_EXT_SOURCE */

    /**
     * Get RichExtSource specified by ID.
     * @param id id of richExtSource
     * @return Found richExtSource or null if not such found.
     * @throws DatabaseIntegrityException More than one RichExtSource with same ID found.
     */
    RichExtSource getRichExtSource(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichExtSources.
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    List<RichExtSource> getRichExtSources();
    
    /**
     * Get RichExtSources with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichExtSource
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    List<RichExtSource> getRichExtSourcesByName(String name);

    /**
     * Get RichExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of RichExtSource
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    List<RichExtSource> getRichExtSourcesByType(String type);

    /**
     * Get RichExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichExtSources
     * @return List of RichExtSources, empty list if nothing has been found.
     */
    List<RichExtSource> getRichExtSourcesHavingAttrs(List<InputAttribute> attrs);

    /* ATTRIBUTES */

    /**
     * Get attributes of ExtSource specified by ID.
     * @param id id of ExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    List<PerunAttribute> getExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;


}
