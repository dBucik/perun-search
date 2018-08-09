package cz.muni.ics.mappers;

import cz.muni.ics.models.Relation;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationMapper implements RowMapper<Relation> {

	@Override
	public Relation mapRow(ResultSet rs, int i) throws SQLException {

		Relation res = MappersUtils.mapRelation(rs, new Relation());

		JSONObject attrsJson = new JSONObject(rs.getString("attributes"));
		res.setAttributes(MappersUtils.getAttributes(attrsJson));

		return res;
	}

}
