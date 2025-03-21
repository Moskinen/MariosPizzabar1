//Mo kode starter her grundet github fejl

import java.util.*;
import java.text.SimpleDateFormat;


public class Bestillinger implements Comparable<Bestillinger> {

    private String name;
    private int number;
    private double price;
    private int amount;
    private List<Bestillinger> bestillingsListe;
    private Date pickupTime;
    private int orderNumber;

    public Bestillinger(String name, int number, double price, int amount, Date pickupTime, int orderNumber) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.amount = amount;
        this.pickupTime = pickupTime;
        this.orderNumber = orderNumber;
    }

    public Bestillinger(int amount, Date pickupTime, int number){
        this.amount = amount;
        this.pickupTime = pickupTime;
        this.number = number;
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
        return "Bestilling: " + name + " | Nummer: " + number + " | Antal: " + amount + " | pris: " + price;
    }

    public void bestillingsListe() {
        this.bestillingsListe = new ArrayList<>();
    }

    public void tilføjBestilling(Bestillinger bestilling) {
        bestillingsListe.add(bestilling);
    }


    @Override
    public int compareTo(Bestillinger other) {
        return this.pickupTime.compareTo(other.pickupTime);
    }
}


//Mo kode afslutter her

