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
    List<ExtSource> getExtSourcesWithAttrs(List<InputAttribute> attrs);
}
