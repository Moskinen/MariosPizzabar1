//Mo kode starter her grundet github fejl
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bestillinger {

        private String name;
        private int number;
        private double price;
        private int amount;
        private List <Bestillinger> bestillingsListe;

        public Bestillinger(String name, int number, double price, int amount){
            this.name = name;
            this.number = number;
            this.price = price;
            this.amount = amount;
        }
        public String getName(){ return name; }
        public int getNumber(){ return number; }
        public double getPrice(){ return price; }
        public int getAmount(){ return amount; }

        public void setName(String name){ this.name = name; }
        public void setNumber(int number){ this.number = number; }
        public void setPrice(double price){ this.price = price; }
        public void setAmount(int amount){ this.amount = amount; }

        @Override
        public String toString(){
            return "Bestilling: " + name + " | Nummer: " + number + " | Pris: " + price + " | Antal: " + amount;
        }

        public void bestillingsListe(){
            this.bestillingsListe = new ArrayList <>();
        }

        public void tilføjBestilling(Bestillinger bestilling){
            bestillingsListe.add(bestilling);
        }
        public void visBestillinger(){
            if (bestillingsListe.isEmpty()) {
                System.out.println("Ingen aktive bestillinger");
            }else{
                for (Bestillinger b : bestillingsListe){
                    System.out.println(b);
                }
            }
        }
    }
    //Mo kode afslutter her

