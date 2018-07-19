package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.richEntities.RichUser;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource dataSource);

    List<User> getUsers(List<InputAttribute> core);

    List<RichUser> getRichUsers(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichUser> getCompleteRichUsers(List<InputAttribute> core, List<InputAttribute> attrs);

}
