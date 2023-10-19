package learn.platformShooter.data;

import learn.platformShooter.data.mappers.PlayerCharacterMapper;
import learn.platformShooter.models.PlayerCharacter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PlayerCharacterJdbcTemplateRepository implements PlayerCharacterRepository{
    JdbcTemplate jdbcTemplate;
    PlayerCharacterJdbcTemplateRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    @Override
    public List<PlayerCharacter> findAll() {
        final String sql ="select player_character_id, user_id, time_played_in_seconds, characters_level, "
                + "max_health, health, damage, speed, healing_potions "
                + "from player_character;";
        return jdbcTemplate.query (sql,new PlayerCharacterMapper ());
    }

    @Override
    public PlayerCharacter findByUserId(int userId) {
        final String sql ="select player_character_id, user_id, time_played_in_seconds, characters_level, "
                + "max_health, health, damage, speed, healing_potions "
                + "from player_character "
                + "where user_id = ?;";
        return jdbcTemplate.query (sql,new PlayerCharacterMapper (),userId).stream ().findFirst ().orElse (null);
    }

    @Override
    public PlayerCharacter findByPlayerId(int playerCharacterId) {
        final String sql ="select player_character_id, user_id, time_played_in_seconds, characters_level, "
                + "max_health, health, damage, speed, healing_potions "
                + "from player_character "
                + "where player_character_id = ?;";
        return jdbcTemplate.query (sql,new PlayerCharacterMapper (),playerCharacterId).stream ().findFirst ().orElse (null);
    }

    @Override
    public PlayerCharacter add(PlayerCharacter playerCharacter) {
        final String sql = "insert into player_character ( user_id, time_played_in_seconds, characters_level,max_health, health, damage, speed, healing_potions )"
                + "values (?,?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder ();
        int rowsAffected = jdbcTemplate.update (connection -> {
            PreparedStatement ps = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt (1,playerCharacter.getUserId ());
            ps.setInt (2,playerCharacter.getTimePlayedInSeconds ());
            ps.setDouble (3,playerCharacter.getCharactersLevel ());
            ps.setDouble (4, playerCharacter.getMaxHealth ());
            ps.setDouble (5, playerCharacter.getHealth ());
            ps.setDouble (6, playerCharacter.getDamage ());
            ps.setDouble (7,playerCharacter.getSpeed ());
            ps.setInt (8,playerCharacter.getHealingPotions ());
            return ps;
        },keyHolder);

        if(rowsAffected<=0){
            return null;
        }
        playerCharacter.setPlayerCharacterId (keyHolder.getKey ().intValue ());
        return playerCharacter;
    }

    @Override
    public boolean update(PlayerCharacter playerCharacter) {
        final String sql = "update player_character set "
                +"user_id = ?, "
                +"time_played_in_seconds = ?, "
                +"characters_level = ?, "
                +"max_health = ?, "
                +"health = ?, "
                +"damage = ?, "
                +"speed = ?, "
                +"healing_potions = ? "
                +"where player_character_id = ?;";
        return jdbcTemplate.update (sql,
                playerCharacter.getUserId (),
                playerCharacter.getTimePlayedInSeconds (),
                playerCharacter.getCharactersLevel (),
                playerCharacter.getMaxHealth (),
                playerCharacter.getHealth (),
                playerCharacter.getDamage (),
                playerCharacter.getSpeed (),
                playerCharacter.getHealingPotions (),
                playerCharacter.getPlayerCharacterId ())>0;
    }

    @Override
    public boolean deleteById(int playerCharacterId) {
        return jdbcTemplate.update ("delete from player_character where player_character_id = ?;", playerCharacterId)>0;
    }
}
