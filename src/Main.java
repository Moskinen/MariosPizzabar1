import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    //Scanner for taking the users input
    private final Scanner scanner;

    //List of pizzas on the menu
    private final List<Pizza> menuItems;

    //List of active orders
    private final List<Bestillinger> activeOrders;


    public static void main(String[] args) {
        Main mainProgram = new Main();
        mainProgram.run();

    }

    //Constructor
    public Main() {
        menuItems = new ArrayList<>();
        activeOrders = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadMenuItems();
    }

    //Method with switch case to run the user menu
    public void run() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Indtast dit valg: ");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt valg. Indtast et tal.");
                continue;
            }
            switch (choice) {
                case 1 -> takeOrder(menuItems);
                case 2 -> visBestillinger();
                case 3 -> ordreStatus();
                case 4 -> fjernOrdre();
                /*case 5 -> X5();
                case 6 -> X6();*/
                case 9 -> running = false;
                default -> System.out.println("Dit valg eksistere ikke");
            }
        }

        scanner.close();
    }

    //Printing the user menu
    private void displayMainMenu() {
        System.out.println("\n===== MARIO'S PIZZABAR =====");
        System.out.println("1. Opret ny ordre");
        System.out.println("2. Vis Aktive bestillinger");
        System.out.println("3. Marker ordre som klar til afhentning");
        System.out.println("4. Fjern ordre efter afhentning");
        System.out.println("5. Vis statistik");
        System.out.println("9. Afslut program");
    }

    //Method to take orders as input
    public void takeOrder(List<Pizza> menuItems) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Hvad er navnet på kunden der bestiller?");
        String customerName = scanner.nextLine();
        if (customerName.trim().isEmpty()) {
            System.out.println("Fejl: Kundenavn er påkrævet.");
            return;
        }
        int orderNumber = random.nextInt(100);
        System.out.println("Dit ordrenummer er: " + orderNumber);

        //Method for using Simple Date Format
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        String defaultTime = new SimpleDateFormat("HH:mm").format(cal.getTime());

        System.out.print("Afhentningstid (HH:mm) [standard om 1 time " + defaultTime + "]: ");
        String timeInput = scanner.nextLine();

        if (timeInput.trim().isEmpty()) {
            timeInput = defaultTime;
        }

        Date pickupTime;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            pickupTime = sdf.parse(timeInput);

            Calendar pickupCal = Calendar.getInstance();
            Calendar nowCal = Calendar.getInstance();

            pickupCal.setTime(pickupTime);
            pickupCal.set(Calendar.YEAR, nowCal.get(Calendar.YEAR));
            pickupCal.set(Calendar.MONTH, nowCal.get(Calendar.MONTH));
            pickupCal.set(Calendar.DAY_OF_MONTH, nowCal.get(Calendar.DAY_OF_MONTH));

            pickupTime = pickupCal.getTime();
        } catch (ParseException e) {
            System.out.println("Fejl: Ugyldigt tidsformat. Brug HH:MM");
            scanner.nextLine();
            return;
        }

        boolean readyForPickup = false;

        Bestillinger bestilling = new Bestillinger(customerName, orderNumber, pickupTime, readyForPickup);

        boolean addingPizzaer = true;
        boolean anyPizzaAdded = false;

        int totalAmount = 0;
        while (addingPizzaer) {
            System.out.println("===== PIZZA MENU =====");
            for (Pizza pizza : menuItems) {
                System.out.println(pizza);
            }

            System.out.println("Hvilken pizza vil du tilføje: 0-14");
            int pizzaNumber;
            try {
                pizzaNumber = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Fejl: Indtast et gyldigt nummer.");
                continue;
            }

            if (pizzaNumber == 0) {
                if (anyPizzaAdded){
                    addingPizzaer = false;
                } else {
                    System.out.println("Mindst en pizza skal tilføjes til ordren");
                }
                continue;
            }

            Pizza selectedPizza = null;
            for (Pizza pizza : menuItems) {
                if (pizza.getPizNum() == pizzaNumber) {
                    selectedPizza = pizza;
                    break;
                }
            }

            if (selectedPizza == null) {
                System.out.println("Fejl: Ugyldigt pizzanummer.");
                continue;
            }

            System.out.print("Antal: ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    System.out.println("Fejl: Antal skal være større end 0");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Fejl: Indtast et gyldigt antal.");
                continue;
            }

            totalAmount += quantity;
            bestilling.setAmount(totalAmount);

            bestilling.addItem(new OrderItem(selectedPizza, quantity));

            anyPizzaAdded = true;

            System.out.println("Pizza tilføjet: " + quantity + "x " + selectedPizza.getPizName());
            System.out.println("Vil du tilføje flere pizzaer til ordren? (j/n)");
            String morePizza = scanner.nextLine();
            if (!morePizza.toLowerCase().startsWith("j")) {
                addingPizzaer = false;
            }
        }

        activeOrders.add(bestilling);
    }

    //Method to shows orders added in a sorted format based on pickup time
    public void visBestillinger() {
        Collections.sort(activeOrders);
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive bestillinger");
        } else {
            System.out.println("Hvis ordren er markeret med grøn er den klar til afhentning");
            for (Bestillinger bestilling : activeOrders) {

                if (bestilling == activeOrders.getFirst()) {
                    System.out.println(bestilling.redToString());
                } else if (bestilling.getReadyForPickup()) {
                    System.out.println(bestilling.greenToString());
                } else {
                    System.out.println(bestilling);
                }
            }
        }
    }


    //Method to remove an order from the active order list
    public void ordreStatus() {
        Collections.sort(activeOrders);
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive bestillinger");
            return;
        } else {
            for (Bestillinger bestilling : activeOrders) {

                if (bestilling == activeOrders.getFirst()) {
                    System.out.println(bestilling.redToString());
                } else if (bestilling.getReadyForPickup()) {
                    System.out.println(bestilling.greenToString());
                } else {
                    System.out.println(bestilling);
                }
            }
        }

        System.out.println("Hvilken ordre vil du gerne markere som klar til afhentning. Indtast ordrenummeret");
        int orderToChange;
        try {
            orderToChange = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Indtast et gyldigt ordrenummer.");
            scanner.nextLine();
            return;
        }

        for (Bestillinger bestilling : activeOrders)
            if (orderToChange == bestilling.getOrderNumber()) {
                bestilling.setReadyForPickup(true);
                break;
            }
    }

    //Method to remove an order from the active order list
    public void fjernOrdre() {
        Collections.sort(activeOrders);
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive bestillinger");
            scanner.nextLine();
        } else {
            for (Bestillinger bestilling : activeOrders) {
                if (bestilling == activeOrders.getFirst()) {
                    System.out.println(bestilling.redToString());
                } else if (bestilling.getReadyForPickup()) {
                    System.out.println(bestilling.greenToString());
                } else {
                    System.out.println(bestilling);
                }
            }
        }

        System.out.println("Hvilken ordre vil du gerne slette fra listen. Indtast ordrenummeret");
        int orderToChange;
        try {
            orderToChange = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Indtast et gyldigt ordrenummer.");
            scanner.nextLine();
            return;
        }

        for (Bestillinger bestilling : activeOrders)
            if (orderToChange == bestilling.getOrderNumber()) {
                activeOrders.remove(bestilling);
                break;
            }
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

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
