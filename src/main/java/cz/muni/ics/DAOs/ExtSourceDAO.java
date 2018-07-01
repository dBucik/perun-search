package cz.muni.ics.DAOs;

import cz.muni.ics.models.ExtSource;
import cz.muni.ics.models.InputAttribute;

import javax.sql.DataSource;
import java.util.List;

public interface ExtSourceDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get extSource specified by ID.
     * @param id id of extSource
     * @return Found extSource or null if not such found.
     */
    ExtSource getExtSource(Long id);

    /**
     * Get extSource specified by NAME.
     * @param name name of extSource
     * @return Found extSource or null if not such found.
     */
    ExtSource getExtSourceByName(String name);

    /**
     * Get all extSources.
     * @return List of extSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSources();

    /**
     * Get attributes of extSource specified by ID
     * @param id id of extSource
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    ExtSource getExtSourceAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get extSources that have specified attributes. (Exact matching used)
     * @param attrs attributes of extSources
     * @return List of extSources, empty list if nothing has been found.
     */
    List<ExtSource> getExtSourcesByAttrs(List<InputAttribute> attrs);
}
