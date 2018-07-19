package cz.muni.ics.DAOs;

import cz.muni.ics.models.InputAttribute;
import cz.muni.ics.models.entities.Vo;
import cz.muni.ics.models.richEntities.RichVo;

import javax.sql.DataSource;
import java.util.List;

public interface VoDAO {

    void setDataSource(DataSource dataSource);

    List<Vo> getVos(List<InputAttribute> core);

    List<RichVo> getRichVos(List<InputAttribute> core, List<InputAttribute> attrs,
										 List<String> attrsNames);

    List<RichVo> getCompleteRichVos(List<InputAttribute> core, List<InputAttribute> attrs);

}
