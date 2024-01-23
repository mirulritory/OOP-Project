package model;

public class OrderItem {
    private String drinkName;
    private int quantity;
    private double drinkPrice;

    public OrderItem(String drinkName, int quantity, double drinkPrice) {
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.drinkPrice = drinkPrice;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }
}
