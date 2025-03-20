import java.util.Scanner;

public class Switch {
    public static void switches(){

        // Menu skal erstattes af et navn fra pizza opgaven
        // X Skal navngives
        Menu X = new Menu("MENU", "Choose option:",
                new String[]{
                                "| 1. hej",
                        "| 2. hej",
                        "| 3. hej",
                        "| 4. hej",
                        "| 5. hej",
                        "| 6. hej",
                        "| 9. Quit\n"
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
                case 1 -> X1();
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

    private static void X1(){}
    private static void X2(){}
    private static void X3(){}
    private static void X4(){}
    private static void X5(){}
    private static void X6(){}


}
