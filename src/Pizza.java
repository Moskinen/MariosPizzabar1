public class Pizza {
    //Attributes
    private int pizzaNumber;
    private String pizzaName;
    private String pizzaIngredients;
    private double price;

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

    public String getPizIng() {
        return this.pizzaIngredients;
    }

    public double getPrice() {
        return this.price;
    }

    //toString
    public String toString() {
        return String.format("%-5d%-15s%-65s%.2f\n", pizzaNumber, pizzaName, pizzaIngredients, price);
    }
}
