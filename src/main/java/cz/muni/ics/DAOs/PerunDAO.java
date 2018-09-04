package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.ExtSource;
import cz.muni.ics.models.entities.Facility;
import cz.muni.ics.models.entities.Group;
import cz.muni.ics.models.entities.Host;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.entities.Owner;
import cz.muni.ics.models.entities.Resource;
import cz.muni.ics.models.entities.Service;
import cz.muni.ics.models.entities.User;
import cz.muni.ics.models.entities.UserExtSource;
import cz.muni.ics.models.entities.Vo;

import javax.sql.DataSource;
import java.util.List;

public interface PerunDAO {

	void setDataSource(DataSource dataSource);

	List<ExtSource> getExtSources(List<String> attrsNames, List<InputAttribute> attrs);

	List<Facility> getFacilities(List<String> attrsNames, List<InputAttribute> attrs);

	List<Group> getGroups(List<String> attrsNames, List<InputAttribute> attrs);

	List<Host> getHosts(List<String> attrsNames, List<InputAttribute> attrs);

	List<Member> getMembers(List<String> attrsNames, List<InputAttribute> attrs);

	List<Owner> getOwners(List<String> attrsNames, List<InputAttribute> attrs);

	List<Resource> getResources(List<String> attrsNames, List<InputAttribute> attrs);

	List<Service> getServices(List<String> attrsNames, List<InputAttribute> attrs);

	List<User> getUsers(List<String> attrsNames, List<InputAttribute> attrs);

	List<UserExtSource> getUserExtSources(List<String> attrsNames, List<InputAttribute> attrs);

	List<Vo> getVos(List<String> attrsNames, List<InputAttribute> attrs);
}
