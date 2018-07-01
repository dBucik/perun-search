package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.richEntities.RichUser;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource dataSource);
    
    /* USER */
    
    /**
     * Get User specified by ID.
     * @param id id of User
     * @return Found User or null if not such found.
     * @throws DatabaseIntegrityException More than one User with same ID found.
     */
    User getUser(Long id) throws DatabaseIntegrityException;

    /**
     * Get all Users.
     * @return List of Users, empty list if nothing has been found.
     */
    List<User> getUsers();

    /**
     * Get Users with NAME like specified params.
     * (LIKE operator used, comparing ignores case)
     * @param titleBefore title before the name
     * @param firstName given name
     * @param middleName middle name
     * @param lastName family name
     * @param titleAfter title after the name
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                              String lastName, String titleAfter);

    /**
     * Get Users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersByServiceAcc(boolean isServiceAcc);

    /**
     * Get Users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc);

    /**
     * Get Users that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Users
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersHavingAttrs(List<InputAttribute> attrs);

    /* RICH_USER */

    /**
     * Get User specified by ID.
     * @param id id of User
     * @return Found User or null if not such found.
     * @throws DatabaseIntegrityException More than one User with same ID found.
     */
    RichUser getRichUser(Long id) throws DatabaseIntegrityException;

    /**
     * Get all RichUsers.
     * @return List of RichUsers, empty list if nothing has been found.
     */
    List<RichUser> getRichUsers();

    /**
     * Get RichUsers with NAME like specified params.
     * (LIKE operator used, comparing ignores case)
     * @param titleBefore title before the name
     * @param firstName given name
     * @param middleName middle name
     * @param lastName family name
     * @param titleAfter title after the name
     * @return List of RichUsers found, empty list if nothing has been found.
     */
    List<RichUser> getRichUsersByName(String titleBefore, String firstName, String middleName,
                              String lastName, String titleAfter);

    /**
     * Get RichUsers by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @return List of RichUsers found, empty list if nothing has been found.
     */
    List<RichUser> getRichUsersByServiceAcc(boolean isServiceAcc);

    /**
     * Get RichUsers by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of RichUsers found, empty list if nothing has been found.
     */
    List<RichUser> getRichUsersBySponsoredAcc(boolean isSponsoredAcc);

    /**
     * Get RichUsers that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of RichUsers
     * @return List of RichUsers found, empty list if nothing has been found.
     */
    List<RichUser> getRichUsersHavingAttrs(List<InputAttribute> attrs);

    
    /* ATTRIBUTES */

    /**
     * Get attributes of User specified by ID.
     * @param id id of User
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     * @throws DatabaseIntegrityException More than one User with same ID found.
     */
    List<Attribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;
}
