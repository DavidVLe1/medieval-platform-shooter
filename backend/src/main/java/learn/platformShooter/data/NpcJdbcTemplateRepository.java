package learn.platformShooter.data;

import learn.platformShooter.data.mappers.NpcMapper;
import learn.platformShooter.models.Npc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class NpcJdbcTemplateRepository implements NpcRepository{
    private final JdbcTemplate jdbcTemplate;
    public NpcJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}
    @Override
    public List<Npc> findAll() {
        final String sql = "select npc_id, npc_name, stat_increment_type, stat_increment "
                +"from npc;";
        return jdbcTemplate.query (sql,new NpcMapper ());
    }

    @Override
    public Npc findById(int npcId) {
        final String sql = "select npc_id, npc_name,stat_increment_type, stat_increment "
                +"from npc "
                +"where npc_id = ?;";
        return jdbcTemplate.query (sql, new NpcMapper(),npcId).stream ().findFirst ().orElse (null);
    }

    @Override
    public Npc add(Npc npc) {
        final String sql = "insert into npc (npc_name, stat_increment_type, stat_increment) "
                +"values (?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection -> {
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString (1,npc.getNpcName ());
            ps.setString (2,npc.getStatIncrementType ());
            ps.setDouble (3,npc.getStatIncrement ());
            return ps;
        },keyHolder);
        if(rowsAffected<=0){
            return null;
        }
        npc.setNpcId (keyHolder.getKey ().intValue ());
        return npc;
    }

    @Override
    public boolean update(Npc npc) {
        final String sql = "update npc set "
                +"npc_name = ?, "
                +"stat_increment_type = ?,"
                +"stat_increment = ?, "
                +"where npc_id = ?;";
        return jdbcTemplate.update (sql,
                npc.getNpcName (),
                npc.getStatIncrementType (),
                npc.getStatIncrement (),
                npc.getNpcId ())>0;
    }

    @Override
    @Transactional
    public boolean deleteById(int npcId) {
        return jdbcTemplate.update ("delete from npc where npc_id = ?;", npcId)>0;
    }
}
