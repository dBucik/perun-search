package cz.muni.ics.mappers;

import cz.muni.ics.models.Relation;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelationMapper implements RowMapper<Relation> {

	@NotNull
	@Override
	public Relation mapRow(@NotNull ResultSet rs, int i) throws SQLException {
		JSONObject entityJson = new JSONObject(rs.getString("relation"));
		Relation res = MappersUtils.mapRelation(entityJson, new Relation());

		JSONObject attrsJson = new JSONObject(rs.getString("attrs"));
		res.setAttributes(MappersUtils.getAttributes(attrsJson));

		return res;
	}

}
