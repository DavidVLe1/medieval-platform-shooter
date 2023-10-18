package learn.platformShooter.data.mappers;

import learn.platformShooter.models.Npc;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NpcMapper implements RowMapper<Npc> {
    @Override
    public Npc mapRow(ResultSet resultSet, int i)throws SQLException {
        Npc npc = new Npc ();
        npc.setNpcId (resultSet.getInt ("npc_id"));
        npc.setNpcName (resultSet.getString ("npc_name"));
        npc.setStat_increment_type (resultSet.getString ("stat_boost"));
        return npc;
    }
}
