import java.util.Scanner;

public class Menu {

    //Attributter
    private String menuHeader;
    private String leadText;
    private String[] menuItems;


    public Menu(String menuHeader, String leadText, String[] menuItems) {
        //Konstruktører
        this.menuHeader = menuHeader;
        this.leadText = leadText;
        this.menuItems = menuItems;
    }

    public void showMenu() {

        //Viser menuen menuHeader
        System.out.println(menuHeader);
        for (String item : menuItems) {
            System.out.println(item);
        }
    }

    public int choice() {
        //Tager et tal fra user, som et valg af flere valgmuligheder
        Scanner sc = new Scanner(System.in);
        System.out.println(leadText);
        return sc.nextInt();
    }
}


