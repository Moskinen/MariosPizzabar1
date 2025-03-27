import java.io.Serializable;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

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
