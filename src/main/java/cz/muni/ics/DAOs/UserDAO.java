package cz.muni.ics.DAOs;

import cz.muni.ics.exceptions.DatabaseIntegrityException;
import cz.muni.ics.models.Attribute;
import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource dataSource);

    /**
     * Get User specified by ID.
     * @param id id of User
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return Found User or null if not such found.
     * @throws DatabaseIntegrityException More than one User with same ID found.
     */
    User getUser(Long id, boolean withAttrs) throws DatabaseIntegrityException;

    /**
     * Get all Users.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Users, empty list if nothing has been found.
     */
    List<User> getUsers(boolean withAttrs);

    /**
     * Get attributes of User specified by ID.
     * @param id id of User
     * @param attrs attributes to be fetched
     * @return List of attributes, empty list if nothing has been found.
     * @throws DatabaseIntegrityException More than one User with same ID found.
     */
    List<Attribute> getUserAttrs(Long id, List<String> attrs) throws DatabaseIntegrityException;

    /**
     * Get Users that have specified attributes.
     * (EXACT matching used)
     * @param attrs attributes of Users
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersWithAttrs(List<InputAttribute> attrs, boolean withAttrs);

    /**
     * Get Users with NAME like specified params.
     * (LIKE operator used, comparing ignores case)
     * @param titleBefore title before the name
     * @param firstName given name
     * @param middleName middle name
     * @param lastName family name
     * @param titleAfter title after the name
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersByName(String titleBefore, String firstName, String middleName,
                              String lastName, String titleAfter, boolean withAttrs);

    /**
     * Get Users by specifying if their acc is serviceAcc.
     * @param isServiceAcc TRUE for serviceAccounts, FALSE otherwise.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersByServiceAcc(boolean isServiceAcc, boolean withAttrs);

    /**
     * Get Users by specifying if their acc is sponsored.
     * @param isSponsoredAcc TRUE for sponsoredAccounts, FALSE otherwise.
     * @param withAttrs TRUE if the entity should contain attributes, FALSE otherwise
     * @return List of Users found, empty list if nothing has been found.
     */
    List<User> getUsersBySponsoredAcc(boolean isSponsoredAcc, boolean withAttrs);
}
