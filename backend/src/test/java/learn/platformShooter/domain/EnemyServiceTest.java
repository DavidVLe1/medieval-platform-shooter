package learn.platformShooter.domain;

import learn.platformShooter.data.EnemyRepository;
import learn.platformShooter.models.Enemy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class EnemyServiceTest {
    @Autowired
    EnemyService service;
    @MockBean
    EnemyRepository repository;

    @Test
    void shouldFindGoblinById() {
        Enemy expected = makeEnemy ();
        when(repository.findById (1)).thenReturn(expected);
        Enemy actual = service.findById (1);
        assertEquals (expected,actual);
    }

    @Test
    void shouldFindListOfSmallEnemies() {
        Enemy goblin = makeEnemy ();
        Enemy highElf = makeEnemy ();
        highElf.setEnemyId (3);
        highElf.setEnemyName ("High Elf");
        highElf.setEnemyType ("Small");
        highElf.setDamage (10.0);
        highElf.setHealth (150.0);
        highElf.setSpeed (7.0);
        Enemy chicken = makeEnemy ();
        chicken.setEnemyId (4);
        chicken.setEnemyName ("Chicken");
        chicken.setEnemyType ("Small");
        chicken.setDamage (5.0);
        chicken.setHealth (10.0);
        chicken.setSpeed (3.0);
        List<Enemy> enemyList = List.of (goblin,highElf,chicken);
        when(repository.findByType ("Small")).thenReturn(enemyList);
        List<Enemy> actual = service.findByType ("Small");
        assertEquals (enemyList,actual);
    }

    @Test
    void ShouldNotFindByTypeBig() {
        when(repository.findByType ("Big")).thenReturn (List.of ());
        List<Enemy> bigEnemies = service.findByType ("Big");
        assertEquals (bigEnemies,List.of ());
    }

    @Test
    void shouldAddWhenValid() {
        Enemy enemy = makeEnemy ();
        enemy.setEnemyId (0);
        Enemy expected = makeEnemy ();
        expected.setEnemyId (5);
        when(repository.add (enemy)).thenReturn (expected);

        Result<Enemy> result = service.add (enemy);
        assertEquals (ResultType.SUCCESS, result.getType ());
        assertEquals (expected,result.getPayload());
    }
    @Test
    void shouldNotAddInvalid(){
        //can't add null
        Result<Enemy> result = service.add (null);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add when enemyId is not 0
        Enemy enemy = makeEnemy ();
        enemy.setEnemyId (2);
        result=service.add (enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("enemyId cannot be set for `add` operation.", result.getMessages().get(0));

        // Should not add null or blank name
        enemy.setEnemyName (null);
        result = service.add(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Name is required", result.getMessages().get(0));

        enemy.setEnemyName ("");
        result = service.add(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Name is required", result.getMessages().get(0));

        // Should not add null type
        enemy = makeEnemy ();
        enemy.setEnemyType (null);
        result = service.add(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Type is required", result.getMessages().get(0));

        // Should not add zero damage
        enemy = makeEnemy ();
        enemy.setDamage (0);
        result = service.add(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Damage is required to be more than 0.", result.getMessages().get(0));

        // Should not add zero health
        enemy = makeEnemy ();
        enemy.setHealth (0);
        result = service.add(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Health is required to be more than 0.", result.getMessages().get(0));

        // Should not add zero speed
        enemy = makeEnemy ();
        enemy.setSpeed (0);
        result = service.add(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Speed is required to be more than 0.", result.getMessages().get(0));

    }
    @Test
    void shouldUpdate() {
        Enemy enemy = makeEnemy ();
        when(repository.update (enemy)).thenReturn (true);
        Result<Enemy> actual=service.update (enemy);
        assertEquals (ResultType.SUCCESS,actual.getType ());
    }
    @Test
    void shouldNotUpdate(){
        // Should not update null enemy
        Result<Enemy> result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update enemy with enemyId <= 0
        Enemy enemy = makeEnemy ();
        enemy.setEnemyId (0);// enemyId = 0
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("enemyId must be set for `update` operation.", result.getMessages().get(0));

        // Should not update when enemy not found
        enemy.setEnemyId (20);
        when(repository.update(enemy)).thenReturn(false);
        result = service.update(enemy);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("enemyId: %s, not found", 20), result.getMessages().get(0));

        // Should not update with null or blank name
        enemy.setEnemyName (null);
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Name is required", result.getMessages().get(0));

        enemy.setEnemyName ("");
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Name is required", result.getMessages().get(0));

        // Should not update with null type
        enemy = makeEnemy ();
        enemy.setEnemyType (null);
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Type is required", result.getMessages().get(0));

        // Should not update with zero Damage
        enemy = makeEnemy ();
        enemy.setDamage (0);
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Damage is required to be more than 0.", result.getMessages().get(0));

        // Should not update with zero Health
        enemy = makeEnemy ();
        enemy.setHealth (0);
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Health is required to be more than 0.", result.getMessages().get(0));

        // Should not update with zero Speed
        enemy = makeEnemy ();
        enemy.setSpeed (0);
        result = service.update(enemy);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Enemy Speed is required to be more than 0.", result.getMessages().get(0));
    }

    @Test
    void shouldDeleteById() {
        when(repository.deleteById (1)).thenReturn (true);
        Result<Enemy> result = service.deleteById (1);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void shouldNotDeleteById(){
        when(repository.deleteById (20)).thenReturn (false);

        Result<Enemy> result= service.deleteById (20);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("Enemy id: %s, not found", 20), result.getMessages().get(0));
    }

    Enemy makeEnemy(){
        //enemyId:'1', name:'Goblin', type:'Small', damage:'10', health:'50', speed:'5'
        Enemy enemy = new Enemy ();
        enemy.setEnemyId (1);
        enemy.setEnemyName ("Goblin");
        enemy.setEnemyType ("Small");
        enemy.setDamage (10.0);
        enemy.setHealth (50.0);
        enemy.setSpeed (5.0);
        return enemy;
    }
}