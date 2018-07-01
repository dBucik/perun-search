package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Group;
import cz.muni.ics.models.InputAttribute;

import javax.sql.DataSource;
import java.util.List;

public interface GroupDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get Group specified by ID.
     * @param id id of Group
     * @return Found Group or null if not such found.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    Group getGroup(Long id) throws DatabaseIntegrityException;

    /**
     * Get Groups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Group
     * @return List of Groups, empty list if nothing has been found.
     */
    List<Group> getGroupsByName(String name);

    /**
     * Get all Groups.
     * @return List of Groups, empty list if nothing has been found.
     */
    List<Group> getGroups();

    /**
     * Get attributes of Group specified by ID.
     * @param id id of Group
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    List<Attribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get Groups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Groups
     * @return List of Groups found, empty list if nothing has been found.
     */
    List<Group> getGroupsWithAttrs(List<InputAttribute> attrs);

    /**
     * Get parent group of Group specified by ID.
     * @param childGroupId id of Group whose parent has to be found
     * @return Parent Group.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     *                                    No parent group found for Group with specified ID.
     */
    Group getParentGroup(Long childGroupId) throws DatabaseIntegrityException;

    /**
     * Get all Groups of VO specified by ID.
     * @param voId id of VO
     * @return List of Groups, empty list if nothing has been found.
     */
    List<Group> getGroupsOfVo(Long voId);
}
