package model;

public class Menu {
	
	private int drinkID;
	private String drinkName;
	private double drinkPrice;
	
	
	
	public Menu(int drinkID, String drinkName, double drinkPrice) {
        this.drinkID = drinkID;
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
    }
	public Menu() {
        // You can leave it empty or initialize default values if needed
    }
	public int getDrinkID() {
		return drinkID;
	}
	public void setDrinkID(int drinkID) {
		this.drinkID = drinkID;
	}
	public String getDrinkName() {
		return drinkName;
	}
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	public double getDrinkPrice() {
		return drinkPrice;
	}
	public void setDrinkPrice(double drinkPrice) {
		this.drinkPrice = drinkPrice;
	}
	
}
