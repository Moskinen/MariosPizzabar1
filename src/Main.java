import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Pizza> menuItems;
    private List<Bestillinger> bestillingsListe;

    public static void main(String[] args) {

    }

    public Main() {
        menuItems = new ArrayList<>();
        bestillingsListe = new ArrayList<>();

        loadMenuItems();
    }

    public void displayMenu() {
    boolean running = true;
    Scanner sc = new Scanner(System.in);

    // "showMenu" skal forstille sig at være vores "System-menu"
        while (running) {
        X.showMenu();
        int choice = X.choice();
        // "choice" skal forstille sig at være en metode som tager et tal fra user, som et valg af flere valgmuligheder

        // "X" Skal erstattes med funktionernes navne
        switch (choice) {
            case 1 -> ();
            case 2 -> X2();
            case 3 -> X3();
            case 4 -> X4();
            case 5 -> X5();
            case 6 -> X6();
            case 9 -> running = false;
            default -> System.out.println("Dit valg eksistere ikke");
        }
    }
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


    private void loadMenuItems() {
        menuItems.add(new Pizza(1, "Vesuvio:", "Tomatsauce, ost, skinke og oregano", 57));
        menuItems.add(new Pizza(2, "Amerikaner:", "Tomatsauce, ost, oksefars og oregano", 53));
        menuItems.add(new Pizza(3, "Cacciatore:", "Tomatsauce, ost, pepperoni og oregano", 57));
        menuItems.add(new Pizza(4, "Carbona:", "Tomatsauce, ost, kødsauce, spaghetti, coctailpølser og oregano", 63));
        menuItems.add(new Pizza(5, "Dennis:", "Tomatsauce, ost, skine, pepperoni, coctailpølser og oregano", 65));
        menuItems.add(new Pizza(6, "Bertil:", "Tomatsauce, ost, bacon, oregano", 57));
        menuItems.add(new Pizza(7, "Silvia:", "Tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano", 61));
        menuItems.add(new Pizza(8, "Victoria:", "Tomatsauce, ost, skinke, ananas, champignon, løg og oregano", 61));
        menuItems.add(new Pizza(9, "Toronfo:", "Tomatsauce, ost, skinke, bacon, kebab, chili og oregano", 61));
        menuItems.add(new Pizza(10, "Capricciosa:", "Tomatsauce, ostm skinke, champignon, oregano", 61));
        menuItems.add(new Pizza(11, "Hawaii:", "Tomatsauce, ost, skinke, ananas og oregano", 61));
        menuItems.add(new Pizza(12, "Le Blissola:", "Tomatsauce, ost, skinke, rejer og oregano", 61));
        menuItems.add(new Pizza(13, "Venezia:", "Tomatsauce, ost, skinke, bacon og oregano", 61));
        menuItems.add(new Pizza(14, "Mafia:", "Tomatsause, ost, pepperoni, bacon, løg, og oregano", 61));
    }
}
