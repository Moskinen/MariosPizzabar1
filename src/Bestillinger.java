//Mo kode starter her grundet github fejl

import java.util.*;
import java.text.SimpleDateFormat;


public class Bestillinger {

    private String name;
    private int number;
    private double price;
    private int amount;
    private List<Bestillinger> bestillingsListe;
    private Date pickupTime;
    private int orderNumber;

    public Bestillinger(String name, int number, double price, int amount) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.amount = amount;
        this.pickupTime = pickupTime;
        this.orderNumber = orderNumber;
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

    public void visBestillinger() {
        if (bestillingsListe.isEmpty()) {
            System.out.println("Ingen aktive bestillinger");
        } else {
            for (Bestillinger b : bestillingsListe) {
                System.out.println(b);
            }
        }
        Methods.sortTime1();
    }

    public void takeOrder(List<Pizza> menuItems) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hvor mange pizzaer er der i bestillingen?");
        int ammountPizza = scanner.nextInt();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        String defaultTime = new SimpleDateFormat("HH:mm").format(cal.getTime());

        System.out.print("Afhentningstid (HH:mm) [standard om 45 Min " + defaultTime + "]: ");
        String timeInput = scanner.nextLine();

        for (int i = 1; ammountPizza > i; i++) {
            System.out.println("Hvilken nr på menukortet er pizza nr. " + i + "?");
            int pizzaNumber = scanner.nextInt();

            Pizza selectedPizza = null;
            for (Pizza pizza : menuItems) {
                if (pizza.getPizNum() == pizzaNumber) {
                    selectedPizza = pizza;
                    if (selectedPizza != null) {
                        int orderNumber = bestillingsListe.size() + 1;
                        break;
                    }
                }
            }
        }
    }

}
//Mo kode afslutter her

