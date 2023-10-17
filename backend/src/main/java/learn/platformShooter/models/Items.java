package learn.platformShooter.models;

public class Items {
    private int itemId;
    private String name;
    private String itemDescription;
    private String type;
    private double statIncrement;

    public Items() {
    }

    public Items(int itemId , String name , String itemDescription , String type , double statIncrement) {
        this.itemId = itemId;
        this.name = name;
        this.itemDescription = itemDescription;
        this.type = type;
        this.statIncrement = statIncrement;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getStatIncrement() {
        return statIncrement;
    }

    public void setStatIncrement(double statIncrement) {
        this.statIncrement = statIncrement;
    }
}
