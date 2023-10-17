package learn.platformShooter.models;

public class GameEvents {
    private int gameEventsId;
    private int playerCharacterId;
    private int bossesKilled;
    private boolean legendaryItemObtained;
    private boolean gameCompleted;

    public GameEvents() {
    }

    public GameEvents(int gameEventsId , int playerCharacterId , int bossesKilled , boolean legendaryItemObtained , boolean gameCompleted) {
        this.gameEventsId = gameEventsId;
        this.playerCharacterId = playerCharacterId;
        this.bossesKilled = bossesKilled;
        this.legendaryItemObtained = legendaryItemObtained;
        this.gameCompleted = gameCompleted;
    }

    public int getGameEventsId() {
        return gameEventsId;
    }

    public void setGameEventsId(int gameEventsId) {
        this.gameEventsId = gameEventsId;
    }

    public int getPlayerCharacterId() {
        return playerCharacterId;
    }

    public void setPlayerCharacterId(int playerCharacterId) {
        this.playerCharacterId = playerCharacterId;
    }

    public int getBossesKilled() {
        return bossesKilled;
    }

    public void setBossesKilled(int bossesKilled) {
        this.bossesKilled = bossesKilled;
    }

    public boolean isLegendaryItemObtained() {
        return legendaryItemObtained;
    }

    public void setLegendaryItemObtained(boolean legendaryItemObtained) {
        this.legendaryItemObtained = legendaryItemObtained;
    }

    public boolean isGameCompleted() {
        return gameCompleted;
    }

    public void setGameCompleted(boolean gameCompleted) {
        this.gameCompleted = gameCompleted;
    }
}
