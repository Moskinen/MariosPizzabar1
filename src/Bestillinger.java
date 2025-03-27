import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;


public class Bestillinger implements Serializable, Comparable<Bestillinger> {

    //Attributes
    private static final long serialVersionUID = 1L;

    private String name;
    private int number;
    private double price;
    private int amount;
    private List<OrderItem> bestillingsListe;
    private Date pickupTime;
    private int orderNumber;
    public boolean readyForPickup;

    //Constructor
    public Bestillinger(String name, int orderNumber, Date pickupTime, boolean readyForPickup) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.pickupTime = pickupTime;
        this.bestillingsListe = new ArrayList<>();
        this.readyForPickup = readyForPickup;

    }

    //Metode til at tilføje ordre til arraylist
    public void addItem(OrderItem bestilling) {
        bestillingsListe.add(bestilling);
    }

    //Getters
    public List<OrderItem> getBestillingsListe() {
        return bestillingsListe;
    }

    public String getName() {
        return name;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public boolean getReadyForPickup() {
        return readyForPickup;
    }

    //Setters
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setReadyForPickup(boolean readyForPickup) {
        this.readyForPickup = readyForPickup;
    }

    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : bestillingsListe) {
            total += item.getAmount() * item.getPizza().getPrice();
        }
        return total;
    }


    //Farve metoder
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_RESET = "\u001B[0m";



    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bestilling: ").append(name)
                .append(" | Ordrenummer: ").append(orderNumber)
                .append(" | Afhentningstid: ").append(new SimpleDateFormat("HH:mm").format(pickupTime))
                .append(" | Pizzaer:");

        for (OrderItem item : bestillingsListe) {
            sb.append("\n  - ")
                    .append(item.getAmount())
                    .append("x ")
                    .append("#")
                    .append(item.getPizza().getPizNum())
                    .append(" ")
                    .append(item.getPizza().getPizName())
                    .append(" (")
                    .append(item.getPizza().getPrice())
                    .append(" kr pr. stk)");
        }

        sb.append("\n  Total: ").append(getTotalPrice()).append(" kr");

        return sb.toString();
    }

    // Simplified green toString method
    public String greenToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_GREEN)
                .append("Bestilling: ").append(name)
                .append(" | Ordrenummer: ").append(orderNumber)
                .append(" | Afhentningstid: ").append(new SimpleDateFormat("HH:mm").format(pickupTime))
                .append(" | Pizzaer:");

        for (OrderItem item : bestillingsListe) {
            sb.append("\n  - ")
                    .append(item.getAmount())
                    .append("x ")
                    .append(item.getPizza().getPizName());
        }

        sb.append(ANSI_RESET);
        return sb.toString();

    }

    // Simplified red toString method
    public String redToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_RED)
                .append("Bestilling: ").append(name)
                .append(" | Ordrenummer: ").append(orderNumber)
                .append(" | Afhentningstid: ").append(new SimpleDateFormat("HH:mm").format(pickupTime))
                .append(" | Pizzaer:");

        for (OrderItem item : bestillingsListe) {
            sb.append("\n  - ")
                    .append(item.getAmount())
                    .append("x ")
                    .append(item.getPizza().getPizName());
        }

        sb.append(ANSI_RESET);
        return sb.toString();
    }

    //Compare method for pickup time
    @Override
    public int compareTo(Bestillinger other) {
        return this.pickupTime.compareTo(other.pickupTime);
    }
}


