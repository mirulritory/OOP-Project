package model;

public class OrderItem {
    private String drinkName;
    private int quantity;
    private double drinkPrice;
    private double totalPayment;
    private int points;
    

    public OrderItem(String drinkName, int quantity, double drinkPrice) {
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.drinkPrice = drinkPrice * quantity;
        this.totalPayment = quantity * drinkPrice;
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

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
