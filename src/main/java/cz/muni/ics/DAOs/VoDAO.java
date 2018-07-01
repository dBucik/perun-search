package cz.muni.ics.DAOs;

import cz.muni.ics.models.Vo;

import javax.sql.DataSource;
import java.util.List;

public interface VoDAO {

    void setDataSource(DataSource ds);

    Vo getVo(Long id);

    List<Vo> getVos();
}
