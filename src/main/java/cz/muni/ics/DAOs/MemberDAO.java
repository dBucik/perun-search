package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Member;

import javax.sql.DataSource;
import java.util.List;

public interface MemberDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get Member specified by ID.
     * @param id id of Member
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found Member or null if not such found.
     * @throws DatabaseIntegrityException More than one Member with same ID found.
     */
    Member getMember(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get all Members.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, null if nothing has been found.
     */
    List<Member> getMembers(boolean withAttrs);

    /**
     * Get attributes of Member specified by ID.
     * @param id id of Member
     * @param attrs attributes to be fetched
     * @return List of attributes
     * @throws DatabaseIntegrityException More than one Member with same ID found.
     */
    List<Attribute> getMemberAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get Members that have specified attributes. (Exact matching used)
     * @param attrs attributes of Members
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members found, empty list if nothing has been found.
     */
    List<Member> getMembersWithAttrs(List<InputAttribute> attrs, boolean withAttrs);

    /**
     * Get Members of user specified by ID.
     * @param userId id of user
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    List<Member> getMembersOfUser(Long userId, boolean withAttrs);

    /**
     * Get Members of vo specified by ID.
     * @param voId id of vo.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    List<Member> getMembersOfVo(Long voId, boolean withAttrs);

    /**
     * Get Members with specified STATUS.
     * @param status ACTIVE or EXPIRED values are accepted
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    List<Member> getMembersByStatus(String status, boolean withAttrs);

    /**
     * Get Members with specified value for sponsorship.
     * @param isSponsored TRUE if Member is sponsored, FALSE otherwise
     * @return List of Members, empty list if nothing has been found.
     */
    List<Member> getMembersBySponsored(boolean isSponsored, boolean withAttrs);
}
