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

    public Bestillinger(String name, int number, double price, int amount, Date pickupTime, int orderNumber) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.amount = amount;
        this.pickupTime = pickupTime;
        this.orderNumber = orderNumber;
        this.bestillingsListe = new ArrayList<>();
    }

    public Bestillinger(int amount, Date pickupTime, int number){
        this.amount = amount;
        this.pickupTime = pickupTime;
        this.number = number;
    }

    public Bestillinger (String name, int orderNumber, Date pickupTime){
        this.name = name;
        this.orderNumber = orderNumber;
        this.pickupTime = pickupTime;
        this.bestillingsListe = new ArrayList<>();

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

    public int getOrderNumber(){
        return orderNumber;
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

    public void setPickupTime(Date pickupTime){
        this.pickupTime = pickupTime;
    }

    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Bestilling: " + name +
                " | Ordrenummer: " + orderNumber +
                " | Afhentningstid: " + new SimpleDateFormat("HH:mm").format(pickupTime) +
                " | Antal: " + amount;
    }

    public void bestillingsListe() {
        this.bestillingsListe = new ArrayList<>();
    }

    @Override
    public int compareTo(Bestillinger other) {
        return this.pickupTime.compareTo(other.pickupTime);
    }
}


//Mo kode afslutter her

