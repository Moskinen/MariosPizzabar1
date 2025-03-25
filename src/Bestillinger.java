import java.util.*;
import java.text.SimpleDateFormat;


public class Bestillinger implements Comparable<Bestillinger> {

    private String name;
    private int number;
    private double price;
    private int amount;
    private List<OrderItem> bestillingsListe;
    private Date pickupTime;
    private int orderNumber;
    public boolean readyForPickup;


    public Bestillinger(String name, int orderNumber, Date pickupTime, boolean readyForPickup) {
        this.name = name;
        this.orderNumber = orderNumber;
        this.pickupTime = pickupTime;
        this.bestillingsListe = new ArrayList<>();
        this.readyForPickup = readyForPickup;

    }

    public void addItem(OrderItem bestilling) {
        bestillingsListe.add(bestilling);
    }

    public List<OrderItem> getBestillingsListe() {
        return bestillingsListe;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setReadyForPickup(boolean readyForPickup) {
        this.readyForPickup = readyForPickup;
    }

    @Override
    public String toString() {
        return "Bestilling: " + name +
                " | Ordrenummer: " + orderNumber +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(pickupTime) +
                " | Antal: " + amount;
    }

    //Green to string method

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_RESET = "\u001B[0m";

    public String greenToString() {
        return ANSI_GREEN +
                "Bestilling: " + name +
                " | Ordrenummer: " + orderNumber +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(pickupTime) +
                " | Antal: " + amount +
                ANSI_RESET;
    }

    public String redToString() {
        return ANSI_RED +
                "Bestilling: " + name +
                " | Ordrenummer: " + orderNumber +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(pickupTime) +
                " | Antal: " + amount +
                ANSI_RESET;
    }

    @Override
    public int compareTo(Bestillinger other) {
        return this.pickupTime.compareTo(other.pickupTime);
    }
}


