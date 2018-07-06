package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.attributes.PerunAttribute;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.richEntities.RichGroup;

import javax.sql.DataSource;
import java.util.List;

public interface GroupDAO {

    void setDataSource(DataSource dataSource);

    /* GROUP */

    /**
     * Get Group specified by ID.
     * @param id id of Group
     * @return Found Group or null if not such found.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    Group getGroup(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Groups.
     * @return List of Groups, empty list if nothing has been found.
     */
    List<Group> getGroups();

    /**
     * Get Groups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of Group
     * @return List of Groups, empty list if nothing has been found.
     */
    List<Group> getGroupsByName(String name);

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

    /**
     * Get Groups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Groups
     * @return List of Groups found, empty list if nothing has been found.
     */
    List<Group> getGroupsHavingAttrs(List<InputAttribute> attrs);

    /* RICH_GROUP */

    /**
     * Get RichGroup specified by ID.
     * @param id id of RichGroup
     * @return Found RichGroup or null if not such found.
     * @throws DatabaseIntegrityException More than one RichGroup with same ID found.
     */
    RichGroup getRichGroup(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichGroups.
     * @return List of RichGroups, empty list if nothing has been found.
     */
    List<RichGroup> getRichGroups();

    /**
     * Get RichGroups withe NAME like specified parameter.
     * (LIKE operator used, comparing ignores case)
     * @param name name of RichGroup
     * @return List of RichGroups, empty list if nothing has been found.
     */
    List<RichGroup> getRichGroupsByName(String name);

    /**
     * Get parent richGroup of RichGroup specified by ID.
     * @param childRichGroupId id of RichGroup whose parent has to be found
     * @return Parent RichGroup.
     * @throws DatabaseIntegrityException More than one RichGroup with same ID found.
     *                                    No parent richGroup found for RichGroup with specified ID.
     */
    RichGroup getParentRichGroup(Long childRichGroupId) throws DatabaseIntegrityException;

    /**
     * Get all RichGroups of VO specified by ID.
     * @param voId id of VO
     * @return List of RichGroups, empty list if nothing has been found.
     */
    List<RichGroup> getRichGroupsOfVo(Long voId);

    /**
     * Get RichGroups that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichGroups
     * @return List of RichGroups found, empty list if nothing has been found.
     */
    List<RichGroup> getRichGroupsHavingAttrs(List<InputAttribute> attrs);

    /* ATTRIBUTES */

    /**
     * Get attributes of Group specified by ID.
     * @param id id of Group
     * @param attrs attributes to be fetched
     * @return List of attributes.
     * @throws DatabaseIntegrityException More than one Group with same ID found.
     */
    List<PerunAttribute> getGroupAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

}
