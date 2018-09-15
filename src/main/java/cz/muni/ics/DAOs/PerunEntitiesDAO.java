package cz.muni.ics.DAOs;

import cz.muni.ics.models.attributes.InputAttribute;
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

public interface PerunEntitiesDAO {

	void setDataSource(DataSource dataSource);

	List<ExtSource> getExtSources(List<InputAttribute> core);

	List<Facility> getFacilities(List<InputAttribute> core);

	List<Group> getGroups(List<InputAttribute> core);

	List<Host> getHosts(List<InputAttribute> core);

	List<Member> getMembers(List<InputAttribute> core);

	List<Owner> getOwners(List<InputAttribute> core);

	List<Resource> getResources(List<InputAttribute> core);

	List<Service> getServices(List<InputAttribute> core);

	List<User> getUsers(List<InputAttribute> core);

	List<UserExtSource> getUserExtSources(List<InputAttribute> core);

	List<Vo> getVos(List<InputAttribute> core);

}
