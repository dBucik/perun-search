package cz.muni.ics.DAOs;

import cz.muni.ics.models.Member;

import javax.sql.DataSource;
import java.util.List;

public interface MemberDAO {

    void setDataSource(DataSource dataSource);

    Member getMember(Long id);

    List<Member> getMembers();
}
