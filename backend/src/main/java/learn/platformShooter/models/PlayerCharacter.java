package learn.platformShooter.models;

import java.util.Objects;

public class PlayerCharacter {
    private int playerCharacterId;
    private int userId;
    private int timePlayedInSeconds;
    private double charactersLevel;
    private double maxHealth;
    private double health;
    private double damage;
    private double speed;
    private int healingPotions;

    public PlayerCharacter() {
    }

    public PlayerCharacter(int playerCharacterId , int userId , int timePlayedInSeconds , double charactersLevel ,
                           double maxHealth , double health , double damage , double speed , int healingPotions) {
        this.playerCharacterId = playerCharacterId;
        this.userId = userId;
        this.timePlayedInSeconds = timePlayedInSeconds;
        this.charactersLevel = charactersLevel;
        this.maxHealth = maxHealth;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.healingPotions = healingPotions;
    }

    public int getPlayerCharacterId() {
        return playerCharacterId;
    }

    public void setPlayerCharacterId(int playerCharacterId) {
        this.playerCharacterId = playerCharacterId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimePlayedInSeconds() {
        return timePlayedInSeconds;
    }

    public void setTimePlayedInSeconds(int timePlayedInSeconds) {
        this.timePlayedInSeconds = timePlayedInSeconds;
    }

    public double getCharactersLevel() {
        return charactersLevel;
    }

    public void setCharactersLevel(double charactersLevel) {
        this.charactersLevel = charactersLevel;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHealingPotions() {
        return healingPotions;
    }

    public void setHealingPotions(int healingPotions) {
        this.healingPotions = healingPotions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        PlayerCharacter that = (PlayerCharacter) o;
        return playerCharacterId == that.playerCharacterId && userId == that.userId && timePlayedInSeconds == that.timePlayedInSeconds && Double.compare (charactersLevel , that.charactersLevel) == 0 && Double.compare (maxHealth , that.maxHealth) == 0 && Double.compare (health , that.health) == 0 && Double.compare (damage , that.damage) == 0 && Double.compare (speed , that.speed) == 0 && healingPotions == that.healingPotions;
    }

    @Override
    public int hashCode() {
        return Objects.hash (playerCharacterId , userId , timePlayedInSeconds , charactersLevel , maxHealth , health , damage , speed , healingPotions);
    }
}
