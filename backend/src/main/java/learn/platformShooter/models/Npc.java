package learn.platformShooter.models;

import java.util.Objects;

public class Npc {
    private int npcId;
    private String npcName;
    private String statIncrementType;
    private Double statIncrement;

    public Npc() {
    }

    public Npc(int npcId , String npcName , String statIncrementType , Double statIncrement) {
        this.npcId = npcId;
        this.npcName = npcName;
        this.statIncrementType = statIncrementType;
        this.statIncrement = statIncrement;
    }

    public int getNpcId() {
        return npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }

    public String getNpcName() {
        return npcName;
    }

    public void setNpcName(String npcName) {
        this.npcName = npcName;
    }

    public String getStatIncrementType() {
        return statIncrementType;
    }

    public void setStatIncrementType(String statIncrementType) {
        this.statIncrementType = statIncrementType;
    }

    public Double getStatIncrement() {
        return statIncrement;
    }

    public void setStatIncrement(Double statIncrement) {
        this.statIncrement = statIncrement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Npc npc = (Npc) o;
        return npcId == npc.npcId && Objects.equals (npcName , npc.npcName) && Objects.equals (statIncrementType , npc.statIncrementType) && Objects.equals (statIncrement , npc.statIncrement);
    }

    @Override
    public int hashCode() {
        return Objects.hash (npcId , npcName , statIncrementType , statIncrement);
    }
}
