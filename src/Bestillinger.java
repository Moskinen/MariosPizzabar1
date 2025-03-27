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

    //To string metode
    public String toString(Bestillinger bestilling, OrderItem item) {
        return "Bestilling: " + name +
                " | Ordrenummer: " + orderNumber +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(pickupTime) +
                " | Pizza" + item.getPizza().getPizName() +
                " | Antal: " + amount;
    }


    //Farve metoder
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_RESET = "\u001B[0m";

    //Green to string method
    public String greenToString(Bestillinger bestilling, OrderItem item) {
        return ANSI_GREEN +
                "Bestilling: " + name +
                " | Ordrenummer: " + orderNumber +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(pickupTime) +
                " | Antal: " + amount +
                ANSI_RESET;
    }

    //Red to string method
    public String redToString(Bestillinger bestilling, OrderItem item) {
        return ANSI_RED +
                "Bestilling: " + bestilling.getName() +
                " | Ordrenummer: " + bestilling.getOrderNumber() +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(bestilling.getPickupTime()) +
                " | Pizza" + item.getPizza().getPizName() +
                " | Antal: " + item.getAmount() +
                ANSI_RESET;
    }

    //Compare method for pickup time
    @Override
    public int compareTo(Bestillinger other) {
        return this.pickupTime.compareTo(other.pickupTime);
    }
}


