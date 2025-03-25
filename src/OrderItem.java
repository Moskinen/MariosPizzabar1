public class OrderItem {
    private final Pizza pizza;
    private final int amount;

    public OrderItem(Pizza pizza, int amount) {
        this.pizza = pizza;
        this.amount = amount;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getAmount() {
        return amount;
    }
}
