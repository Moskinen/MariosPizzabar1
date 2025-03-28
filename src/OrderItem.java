import java.io.Serializable;

public class OrderItem implements Serializable {

    //Attributes
    private static final long serialVersionUID = 1L;

    private final Pizza pizza;
    private final int amount;

    //Constructor
    public OrderItem(Pizza pizza, int amount) {
        this.pizza = pizza;
        this.amount = amount;
    }

    public OrderItem(){
        this.pizza = null;
        this.amount = 0;
    }

    //Getters
    public Pizza getPizza() {
        return pizza;
    }

    public int getAmount() {
        return amount;
    }
}
