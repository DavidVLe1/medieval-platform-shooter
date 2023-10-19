package learn.platformShooter.models;

import java.util.Objects;

public class WorldStats {
    private int worldStatsId;
    private int playerCharacterId;
    private int enemiesKilled;
    private int itemsUsed;
    private int timesDied;

    public WorldStats() {
    }

    public WorldStats(int worldStatsId , int playerCharacterId , int enemiesKilled , int itemsUsed , int timesDied) {
        this.worldStatsId = worldStatsId;
        this.playerCharacterId = playerCharacterId;
        this.enemiesKilled = enemiesKilled;
        this.itemsUsed = itemsUsed;
        this.timesDied = timesDied;
    }

    public int getWorldStatsId() {
        return worldStatsId;
    }

    public void setWorldStatsId(int worldStatsId) {
        this.worldStatsId = worldStatsId;
    }

    public int getPlayerCharacterId() {
        return playerCharacterId;
    }

    public void setPlayerCharacterId(int playerCharacterId) {
        this.playerCharacterId = playerCharacterId;
    }

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    public int getItemsUsed() {
        return itemsUsed;
    }

    public void setItemsUsed(int itemsUsed) {
        this.itemsUsed = itemsUsed;
    }

    public int getTimesDied() {
        return timesDied;
    }

    public void setTimesDied(int timesDied) {
        this.timesDied = timesDied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        WorldStats that = (WorldStats) o;
        return worldStatsId == that.worldStatsId && playerCharacterId == that.playerCharacterId && enemiesKilled == that.enemiesKilled && itemsUsed == that.itemsUsed && timesDied == that.timesDied;
    }

    @Override
    public int hashCode() {
        return Objects.hash (worldStatsId , playerCharacterId , enemiesKilled , itemsUsed , timesDied);
    }
}
