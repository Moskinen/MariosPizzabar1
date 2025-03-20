import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Methods {

    public static int inputAmmount() { //Metode der tager bruger input for antal pizzaer
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Hvor mange pizzaer er der i ordren? (tast 0 for at afslutte)");

            if (!scanner.hasNextInt()) { //Validation af tal
                System.out.println("You need to type a number");
                scanner.next();
                continue;
            }

            int ammountOfPizzas = scanner.nextInt();
            if (ammountOfPizzas == 0) {
                break;
            }
            return ammountOfPizzas;
        }
        return -1;
    }

    public static ArrayList<Integer> inputOrder(int ammountOfPizzas) { //Metode der returnerer en arraylist med ordren
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> order = new ArrayList<>();

        for (int i = 1; i <= ammountOfPizzas; i++) { //For loop der stopper når det angivede antal pizzaer har fået tilsvarende menu nummer
            System.out.println("Hvilket nr. er pizza " + i + " i ordren? \n Afslut med enter");

            while (!scanner.hasNextInt()) {
                System.out.print("Indtast det nummer på menukortet som passer til pizzaen");
            }

            int pizzaOrdered = scanner.nextInt();
            order.add(pizzaOrdered);
            System.out.println("Pizza nr. " + pizzaOrdered + "er blevet tilføjet til bestillingen");
        }
        return order;
    }
    //Jihad kode starter her grundet github problemer
    public static void switches() {

        // Menu skal erstattes af et navn fra pizza opgaven
        // X Skal navngives
        PizzaMenu X = new PizzaMenu(new String[]
                {"1. Navngiv",
                        "2. Navngiv",
                        "3. Navngiv",
                        "4. Navngiv",
                        "5. Navngiv",
                        "6. Navngiv",
                        "9. Quit"
                });

        boolean running = true;
        Scanner sc = new Scanner(System.in);

        // "showMenu" skal forstille sig at være vores "System-menu"
        while (running) {
            X.showMenu();
            int choice = X.choice();
            // "choice" skal forstille sig at være en metode som tager et tal fra user, som et valg af flere valgmuligheder

            // "X" Skal erstattes med funktionernes navne
            switch (choice) {
                case 1 -> X();
                case 2 -> X();
                case 3 -> X();
                case 4 -> X();
                case 5 -> X();
                case 6 -> X();
                case 9 -> running = false;
                default -> System.out.println("Dit valg eksistere ikke");
            }
        }
    }

    public static void sortTime() {

        // "Integer" skal erstattes med vores rigtige class
        // "tid" er bare navngivet for nu
        ArrayList<Integer> tid = new ArrayList<>();
        Collections.sort(tid);
        System.out.println(tid);
    }
    //Jihad kode slutter her

    public static void sortTime1() {


        // "Integer" skal erstattes med vores rigtige class
        // "tid" er bare navngivet for nu
        ArrayList<Integer> tid = new ArrayList<>();
        Collections.sort(tid);
        System.out.println(tid);
    }
}



