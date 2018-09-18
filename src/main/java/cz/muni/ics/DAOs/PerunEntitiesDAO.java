package cz.muni.ics.DAOs;

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

	List<ExtSource> getExtSources(JDBCQuery query);

	List<Facility> getFacilities(JDBCQuery query);

	List<Group> getGroups(JDBCQuery query);

	List<Host> getHosts(JDBCQuery query);

	List<Member> getMembers(JDBCQuery query);

	List<Owner> getOwners(JDBCQuery query);

	List<Resource> getResources(JDBCQuery query);

	List<Service> getServices(JDBCQuery query);

	List<User> getUsers(JDBCQuery query);

	List<UserExtSource> getUserExtSources(JDBCQuery query);

	List<Vo> getVos(JDBCQuery query);

}
