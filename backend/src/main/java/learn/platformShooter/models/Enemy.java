package learn.platformShooter.models;

import java.util.Objects;

public class Enemy {
    private int enemyId;
    private String enemyName;
    private String enemyType;
    private double damage;
    private double health;
    private double speed;

    public Enemy() {
    }

    public Enemy(int enemyId , String enemyName , String enemyType , double damage , double health , double speed) {
        this.enemyId = enemyId;
        this.enemyName = enemyName;
        this.enemyType = enemyType;
        this.damage = damage;
        this.health = health;
        this.speed = speed;
    }

    public int getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Enemy enemy = (Enemy) o;
        return enemyId == enemy.enemyId && Double.compare (damage , enemy.damage) == 0 && Double.compare (health , enemy.health) == 0 && Double.compare (speed , enemy.speed) == 0 && Objects.equals (enemyName , enemy.enemyName) && Objects.equals (enemyType , enemy.enemyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash (enemyId , enemyName , enemyType , damage , health , speed);
    }
}
