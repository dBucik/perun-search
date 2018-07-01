package cz.muni.ics.DAOs;

import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get user specified by ID.
     * @param id id of user
     * @return Found user or null if not such found.
     */
    User getUser(Long id);

    /**
     * Get all users.
     * @return List of users, null if nothing has been found.
     */
    List<User> getUsers();

    /**
     * Get attributes of user specified by ID.
     * @param id id of user
     * @param attrs attributes to be fetched
     * @return List of attributes.
     */
    List<Attribute> getUserAttrs(Long id, List<InputAttribute> attrs);

    /**
     * Get users that have specified attributes. (Exact matching used)
     * @param attrs attributes of users
     * @return List of users found, empty list if nothing has been found.
     */
    List<User> getUsersWithAttrs(List<InputAttribute> attrs);

    /**
     * Get users with specified NAME. (Exact matching)
     * @param titleBefore title before the name
     * @param firstName given name of user
     * @param middleName middle name of user
     * @param lastName family name of user
     * @param titleAfter title after the name
     * @return List of users found, empty list if nothing has been found.
     */
    List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                              String lastName, String titleAfter);

    /**
     * Get users by specifying if their acc is userAcc.
     * @param isuserAcc TRUE for userAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    List<User> getUsersByUserAcc(boolean isuserAcc);

    /**
     * Get users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @return List of users found, empty list if nothing has been found.
     */
    List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc);
}
