package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.ExtSource;
import cz.muni.ics.models.InputAttribute;

import javax.sql.DataSource;
import java.util.List;

public interface ExtSourceDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get ExtSource specified by ID.
     * @param id id of extSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found extSource or null if not such found.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    ExtSource getExtSource(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get all ExtSources.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSources(boolean withAttrs);

    /**
     * Get attributes of ExtSource specified by ID.
     * @param id id of ExtSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one ExtSource with same ID found.
     */
    List<Attribute> getExtSourceAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get ExtSources with NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of ExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesByName(String name, boolean withAttrs);

    /**
     * Get ExtSources with TYPE like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param type type of ExtSource
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesByType(String type, boolean withAttrs);

    /**
     * Get ExtSources that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of ExtSources
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of ExtSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesWithAttrs(List<InputAttribute> attrs, boolean withAttrs);
}
