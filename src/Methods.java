import java.util.ArrayList;
import java.util.Scanner;

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
}


