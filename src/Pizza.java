import java.io.Serializable;

public class Pizza implements Serializable {
    //Attributes
    private static final long serialVersionUID = 1L;

    private final int pizzaNumber;
    private final String pizzaName;
    private final String pizzaIngredients;
    private final double price;

    //Constructor
    public Pizza(int pizzaNumber, String pizzaName, String pizzaIngredients, double price) {
        this.pizzaNumber = pizzaNumber;
        this.pizzaName = pizzaName;
        this.pizzaIngredients = pizzaIngredients;
        this.price = price;
    }

    //Getters
    public int getPizNum() {
        return this.pizzaNumber;
    }

    public String getPizName() {
        return this.pizzaName;
    }

    public double getPrice() {
        return this.price;
    }

    //toString
    public String toString() {
        return String.format("%-5d%-15s%-65s%.2f\n", pizzaNumber, pizzaName, pizzaIngredients, price);
    }
}
