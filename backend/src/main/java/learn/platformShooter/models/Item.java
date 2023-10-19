package learn.platformShooter.models;

import java.util.Objects;

public class Item {
    private int itemId;
    private String name;
    private String itemDescription;
    private String type;
    private double statIncrement;

    public Item() {
    }

    public Item(int itemId , String name , String itemDescription , String type , double statIncrement) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Item item = (Item) o;
        return itemId == item.itemId && Double.compare (statIncrement , item.statIncrement) == 0 && Objects.equals (name , item.name) && Objects.equals (itemDescription , item.itemDescription) && Objects.equals (type , item.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash (itemId , name , itemDescription , type , statIncrement);
    }
}
