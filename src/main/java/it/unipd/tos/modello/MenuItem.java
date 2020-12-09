////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.modello;

public class MenuItem {

    //enum pubblica ->usabile fuori dalla classe
    public enum itemType {
        GELATO, BUDINO, BEVANDA
    };

    private final String name;
    private final double price;
    private final itemType itemType;

    //costruttore
    public MenuItem(String name, double price, itemType itemType) {
        this.name = name;
        this.price = price;
        this.itemType = itemType;
    }

    //getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public itemType getItemType() {
        return itemType;
    }
    //fine getters
}