import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


public class Main {

    //Scanner for taking the users input
    private Scanner scanner;

    //List of pizzas on the menu
    private List<Pizza> menuItems;

    //List of active orders
    private List<Bestillinger> activeOrders;

    //Order history
    public List<Bestillinger> orderHistory;

    //Initialize of order history file
    private final String ORDERS_FILE = "completed_orders.txt";


    public static void main(String[] args) {
        Main mainProgram = new Main();
        mainProgram.run();

    }

    //Constructor to initialize data structure
    public Main() {
        menuItems = new ArrayList<>();
        activeOrders = new ArrayList<>();
        orderHistory = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadCompletedOrders();
        loadMenuItems();

    }

    //Method with switch case to run the user menu
    public void run() {
        boolean running = true;
        while (running) {
            clearScreen();
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

            //Method invocation
            switch (choice) {
                case 1 -> takeOrder(menuItems);
                case 2 -> visBestillinger();
                case 3 -> ordreStatus();
                case 4 -> fjernOrdre();
                case 5 -> showStatistics();
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
        clearScreen();
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
            waitForEnter();
            return;
        }


        boolean readyForPickup = false;
        //Creating a new instance
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
                pizzaNumber = Integer.parseInt(scanner.nextLine()); //parseInt to avoid leftover input when using nextInt
            } catch (NumberFormatException e) {
                System.out.println("Fejl: Indtast et gyldigt nummer.");
                continue;
            }

            //Validation
            if (pizzaNumber == 0) {
                if (anyPizzaAdded) {
                    addingPizzaer = false;
                } else {
                    System.out.println("Mindst en pizza skal tilføjes til ordren");
                }
                continue;
            }

            //Finding the selected piza in the menuItems list
            Pizza selectedPizza = null;
            for (Pizza pizza : menuItems) {
                if (pizza.getPizNum() == pizzaNumber) {
                    selectedPizza = pizza;
                    break;
                }
            }

            //Validation
            if (selectedPizza == null) {
                System.out.println("Fejl: Ugyldigt pizzanummer.");
                continue;
            }

            //User input for amount of pizzas with validation
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

            //Creating an instance of the order
            bestilling.addItem(new OrderItem(selectedPizza, quantity));

            anyPizzaAdded = true;


            System.out.println("Pizza tilføjet: " + quantity + "x " + selectedPizza.getPizName());
            System.out.println("Vil du tilføje flere pizzaer til ordren? (j/n)"); //User input for restarting the loop
            String morePizza = scanner.nextLine();
            if (!morePizza.toLowerCase().startsWith("j")) {
                addingPizzaer = false;
            }
        }

        //Adding the entire order to two arrays
        activeOrders.add(bestilling);
        orderHistory.add(bestilling);

        //Order confirmation
        clearScreen();
        System.out.println("===== ORDRE OPRETTET =====");
        System.out.println("Ordrenummer: #" + bestilling.getOrderNumber());
        System.out.println("Kunde: " + bestilling.getName());
        System.out.println("Afhentningstid: " + new SimpleDateFormat ("HH:mm").format(bestilling.getPickupTime()));
        System.out.println("Pizzaer:");
        for (OrderItem item: bestilling.getBestillingsListe()) {
            System.out.println(" " + item.getAmount() + "x #" + item.getPizza().getPizNum() + " " +
                    item.getPizza().getPizName() + " " + item.getPizza().getPrice() + " kr");
        }
        System.out.println("Total: " + bestilling.getTotalPrice() + " kr");

        waitForEnter();
    }

    //Method to shows orders added in a sorted format based on pickup time
    public void visBestillinger() {
        Collections.sort(activeOrders);
        if (activeOrders.isEmpty()) {
            System.out.println("Ingen aktive bestillinger");
        } else {
            greenText();
            redText();
            for (Bestillinger bestilling : activeOrders) {
                if (bestilling.getReadyForPickup()) {
                    System.out.println(bestilling.greenToString());
                } else if (bestilling == activeOrders.getFirst()) {
                    System.out.println(bestilling.redToString());
                } else {
                    System.out.println(bestilling);
                }
            }
        }
        waitForEnter();
    }


    //Method to mark an order for ready for pickup
    public void ordreStatus() {
        visBestillinger();

        System.out.println("Hvilken ordre vil du gerne markere som klar til afhentning. Indtast ordrenummeret");
        int orderToChange;
        try {
            orderToChange = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Indtast et gyldigt ordrenummer.");
            scanner.nextLine();
            return;
        }

        //Enhanced for loop for changing the boolean as ready for pickup
        for (Bestillinger bestilling : activeOrders) {
            if (orderToChange == bestilling.getOrderNumber()) {
                bestilling.setReadyForPickup(true);
                break;
            }
        }
    }

    //Method to remove an order from the active order list
    public void fjernOrdre() {
        visBestillinger();

        System.out.println("Hvilken ordre vil du gerne slette fra listen. Indtast ordrenummeret");
        int orderToChange;
        try {
            orderToChange = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Indtast et gyldigt ordrenummer.");
            scanner.nextLine();
            return;
        }

        //Enhanced for loop for looping through active orders and deleting an instance based on the order number
        for (Bestillinger bestilling : activeOrders) {
            if (orderToChange == bestilling.getOrderNumber()) {
                activeOrders.remove(bestilling); //Removing the order from the array
                saveCompletedOrders(); //Adding the order to the file
                break;
            }
        }
    }

    //Method to show statistics for most ordered pizza and total revenue
    private void showStatistics() {
        clearScreen();
        System.out.println("===== ORDRESTATISTIK =====");

        if (orderHistory.isEmpty()) {
            System.out.println("Der er ingen afsluttede ordrer at vise statistik for.");
            waitForEnter();
            return;
        }

        // Calculate statistics
        double totalRevenue = 0;
        for (Bestillinger bestilling : orderHistory) {
            totalRevenue += bestilling.getTotalPrice();
        }

        // Create map of pizza popularity
        Map<Integer, Integer> pizzaCount = new HashMap<>();
        for (Bestillinger bestilling : orderHistory) {
            for (OrderItem item : bestilling.getBestillingsListe()) {
                int pizzaNum = item.getPizza().getPizNum();
                pizzaCount.put(pizzaNum, pizzaCount.getOrDefault(pizzaNum, 0) + item.getAmount());
            }
        }

        // Find most popular pizza
        int mostPopularPizzaNum = 0;
        int highestCount = 0;
        for (Map.Entry<Integer, Integer> entry : pizzaCount.entrySet()) {
            if (entry.getValue() > highestCount) {
                mostPopularPizzaNum = entry.getKey();
                highestCount = entry.getValue();
            }
        }

        // Get pizza name
        String mostPopularPizzaName = "Ukendt";
        for (Pizza pizza : menuItems) {
            if (pizza.getPizNum() == mostPopularPizzaNum) {
                mostPopularPizzaName = pizza.getPizName();
                break;
            }
        }

        // Show statistics
        System.out.println("Dagens omsætning: " + totalRevenue);
        System.out.println("Den meste solgte pizza i dag er pizza nr. " + mostPopularPizzaNum + " "
                + mostPopularPizzaName + ". Der er blevet solgt " + highestCount + " stk.");

        waitForEnter();
    }

    //Method for loading the menu items into an arraylist
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

    // Save completed orders to file
    private void saveCompletedOrders() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_FILE))) {
            oos.writeObject(orderHistory);
        } catch (IOException e) {
            System.err.println("Fejl ved gem af ordrer: " + e.getMessage());
        }
    }

    // Load completed orders from file
    @SuppressWarnings("unchecked")
    private void loadCompletedOrders() {
        File file = new File(ORDERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                orderHistory = (List<Bestillinger>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Fejl ved indlæsning af ordrer: " + e.getMessage());
                orderHistory = new ArrayList<>();
            }
        }
    }

    //Method for clearing the console
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    //Method to wait on user input before clearing the console
    private void waitForEnter() {
        System.out.println("\nTryk ENTER for at fortsætte...");
        scanner.nextLine();
    }

    //Farve metoder
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_RESET = "\u001B[0m";

    //Method for printing an explanation for why green text is used
    public static void greenText() {
        System.out.println("Hvis ordren er markeret med " + Bestillinger.ANSI_GREEN + "grøn " + Bestillinger.ANSI_RESET +
                "er den klar til afhentning");
    }

    //Method for printing an explanation for why red text is used
    public static void redText() {
        System.out.println("Hvis ordren er markeret med " + Bestillinger.ANSI_RED + "rød " + Bestillinger.ANSI_RESET + "er det den næste ordre der skal laves");
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
}



