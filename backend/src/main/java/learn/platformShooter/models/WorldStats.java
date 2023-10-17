package learn.platformShooter.models;

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
}
