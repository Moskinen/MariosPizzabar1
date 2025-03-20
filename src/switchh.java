public class switchh {
    public static void switches(){

        // Menu skal erstattes af et navn fra pizza opgaven
        // X Skal navngives
        Menu X = new Menu(new String[]
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

}
