package learn.platformShooter.models;

public class Npc {
    private int npcId;
    private int npcName;
    private String statBoost;

    public Npc() {
    }

    public Npc(int npcId , int npcName , String statBoost) {
        this.npcId = npcId;
        this.npcName = npcName;
        this.statBoost = statBoost;
    }

    public int getNpcId() {
        return npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }

    public int getNpcName() {
        return npcName;
    }

    public void setNpcName(int npcName) {
        this.npcName = npcName;
    }

    public String getStatBoost() {
        return statBoost;
    }

    public void setStatBoost(String statBoost) {
        this.statBoost = statBoost;
    }
}
