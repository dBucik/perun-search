package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.Group;
import cz.muni.ics.models.InputAttribute;

import javax.sql.DataSource;
import java.util.List;

public interface GroupDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get group specified by ID.
     * @param id id of group
     * @return Found group or null if not such found.
     */
    Group getGroup(Long id);

    /**
     * Get group specified by NAME.
     * @param name name of group
     * @return Found group or null if not such found.
     */
    Group getGroupByName(String name);

    /**
     * Get all groups.
     * @return List of groups, empty list if nothing has been found.
     */
    List<Group> getGroups();

    /**
     * Get attributes of group specified by ID.
     * @param id id of group
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getGroupAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get groups that have specified attributes. (Exact matching used)
     * @param attrs attributes of groups
     * @return List of groups found, empty list if nothing has been found.
     */
    List<Group> getGroupsWithAttrs(List<InputAttribute> attrs);

    /**
     * Get parent group of group specified by ID
     * @param childGroupId id of group whose parent has to be found
     * @return Parent group.
     */
    Group getParentGroup(Long childGroupId);

    /**
     * Get all groups of VO specified by ID.
     * @param voId id of VO
     * @return List of groups, empty list if nothing has been found.
     */
    List<Group> getGroupsOfVo(Long voId);
}
