package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.Member;

import javax.sql.DataSource;
import java.util.List;

public interface MemberDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get member specified by ID.
     * @param id id of member
     * @return Found member or null if not such found.
     */
    Member getMember(Long id);

    /**
     * Get all members.
     * @return List of members, null if nothing has been found.
     */
    List<Member> getMembers();

    /**
     * Get attributes of member specified by ID.
     * @param id id of member
     * @param attrs attributes to be fetched
     * @return List of attributes
     */
    List<Attribute> getMemberAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get members that have specified attributes. (Exact matching used)
     * @param attrs attributes of members
     * @return List of members found, empty list if nothing has been found.
     */
    List<Member> getMembersWithAttrs(List<InputAttribute> attrs);

    /**
     * Get members of user specified by ID.
     * @param userId id of user
     * @return List of members, empty list if nothing has been found.
     */
    List<Member> getMembersOfUser(Long userId);

    /**
     * Get members of vo specified by ID.
     * @param voId id of vo.
     * @return List of members, empty list if nothing has been found.
     */
    List<Member> getMembersOfVo(Long voId);

    /**
     * Get members with specified status. (Exact matching)
     * @param status status of members
     * @return List of members, empty list if nothing has been found.
     */
    List<Member> getMembersByStatus(String status);

    /**
     * Get members with specified value for sponsorship.
     * @param isSponsored TRUE if member is sponsored, FALSE otherwise
     * @return List of members, empty list if nothing has been found.
     */
    List<Member> getMembersBySponsored(boolean isSponsored);
}
