package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Member;
import cz.muni.ics.models.richEntities.RichMember;

import javax.sql.DataSource;
import java.util.List;

public interface MemberDAO {

    void setDataSource(DataSource dataSource);

    List<Member> getMembers(List<InputAttribute> core);

    List<RichMember> getRichMembers(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichMember> getCompleteRichMembers(List<InputAttribute> core, List<InputAttribute> attrs);

}
