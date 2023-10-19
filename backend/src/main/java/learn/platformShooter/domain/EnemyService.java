package learn.platformShooter.domain;

import learn.platformShooter.data.EnemyRepository;
import learn.platformShooter.models.Enemy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyService {
    private final EnemyRepository repository;
    public EnemyService(EnemyRepository repository){this.repository=repository;}

    public List<Enemy> findAll(){return repository.findAll ();}

    public Enemy findById(int enemyId){
        return repository.findById (enemyId);
    };
    public List<Enemy> findByType(String enemyType){
        return repository.findByType (enemyType);
    };

    public Result<Enemy> add(Enemy enemy){
        Result<Enemy> result = validate(enemy);

        if (!result.isSuccess()) {
            return result;
        }

        if (enemy.getEnemyId () != 0) {
            result.addMessage("enemyId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        enemy = repository.add(enemy);
        result.setPayload(enemy);
        return result;
    };

    public Result<Enemy> update(Enemy enemy){
        Result<Enemy> result = validate(enemy);
        if (!result.isSuccess()) {
            return result;
        }

        if (enemy.getEnemyId () <= 0) {
            result.addMessage("enemyId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(enemy)) {
            String msg = String.format("enemyId: %s, not found", enemy.getEnemyId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    };

    public Result<Enemy> deleteById(int enemyId){
        Result<Enemy> result = new Result<>();
        if (!repository.deleteById(enemyId)) {
            String msg = String.format("Enemy id: %s, not found", enemyId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    };

    private Result<Enemy> validate(Enemy enemy) {
        Result<Enemy> result = new Result<> ();
        if (enemy == null) {
            result.addMessage("enemy cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(enemy.getEnemyName ())) {
            result.addMessage("Enemy Name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(enemy.getEnemyType ())) {
            result.addMessage("Enemy Type is required", ResultType.INVALID);
        }
        if (enemy.getDamage ()<=0) {
            result.addMessage("Enemy Damage is required to be more than 0.", ResultType.INVALID);
        }
        if (enemy.getHealth ()<=0) {
            result.addMessage("Enemy Health is required to be more than 0.", ResultType.INVALID);
        }
        if(enemy.getSpeed ()<=0){
            result.addMessage("Enemy Speed is required to be more than 0.", ResultType.INVALID);
        }
        return result;
    }
}
