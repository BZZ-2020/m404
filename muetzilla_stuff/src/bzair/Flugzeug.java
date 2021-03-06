package bzair;

import java.time.LocalDate;
import java.util.Random;


/**
 * Does not work for all persons (some problems with the persons on the edge) and just generated persons, no user input
 */
public class Flugzeug {

    public static void main(String[] args) {
        Flugzeug flugzeug = new Flugzeug();
        flugzeug.run();
    }

    public void run() {
        int anzahlReihen = 9;
        //max 9 seats per row
        int anzahlSitzeProReihe = 5;
        Passagier[][] passagiere = new Passagier[anzahlReihen][anzahlSitzeProReihe];
        Passagier positiverPasagier = null;
        for (int i = 0; i < passagiere.length; i++) {
            for (int j = 0; j < passagiere[i].length; j++) {
                String sitzplatzString = String.valueOf((i));
                sitzplatzString += (j + 1);
                int sitzplatzInt = Integer.parseInt(sitzplatzString);
                //Always create the same person but with the chance of 12.5% to be positive
                passagiere[i][j] = new Passagier(sitzplatzInt, "Max", "Mustermann", LocalDate.of(2004, 6, 6), positivPassengar());
            }
        }
        for (int i = 0; i < passagiere.length; i++) {
            for (int j = 0; j < passagiere[i].length; j++) {
                if (passagiere[i][j].getPositivesTestergebnis()) {
                    positiverPasagier = passagiere[i][j];
                }
            }
        }
        if (positiverPasagier != null) {
            int[] sitzplatzPositivePerson = findSeat(positiverPasagier, anzahlReihen);
            Passagier[] quarantaenePassagiere = getQuarantiedPerson(sitzplatzPositivePerson, passagiere);
            System.out.println("Folgende Passagiere müssen sich in Quarantäne begeben: ");
            for (int i = 0; i < quarantaenePassagiere.length; i++) {
                System.out.println(quarantaenePassagiere[i].toString());
            }
        } else {
            System.out.println("Es ist kein Passagier mit einem positivem Test vorhanden.");
        }

    }

    public int[] findSeat(Passagier p, int anzahlReihen) {
        int[] seat = new int[2];
        int sitznummer = p.getSitznummer();
        //Split Seatnumber into values for the Array
        int reihe = sitznummer / anzahlReihen;
        int sitz = sitznummer % anzahlReihen - 1;
        seat[0] = reihe;
        seat[1] = sitz;
        return seat;
    }

    public Passagier[] getQuarantiedPerson(int seat[], Passagier[][] passagiers) {
        Passagier[] betroffenePersonen = new Passagier[8];
        int reihe = seat[0] - 1;
        int sitz = seat[1];
        System.out.println(reihe);
        System.out.println(sitz);
        System.out.println(passagiers.length);

        //Add all quarantied Person and check if there is a valid filed
        if (sitz - 1 >= 0 && reihe - 1 >= 0) betroffenePersonen[1] = passagiers[reihe - 1][sitz - 1];
        if (reihe - 1 >= 0) betroffenePersonen[2] = passagiers[reihe - 1][sitz];
        if (reihe + 1 < passagiers.length && sitz - 1 >= 0) betroffenePersonen[3] = passagiers[reihe + 1][sitz - 1];
        if (reihe + 1 < passagiers.length) betroffenePersonen[4] = passagiers[reihe + 1][sitz];
        if (reihe + 1 < passagiers.length && sitz + 1 < passagiers[reihe + 1].length)
            betroffenePersonen[5] = passagiers[reihe + 1][sitz + 1];
        if (sitz + 1 < passagiers[reihe].length) betroffenePersonen[6] = passagiers[reihe][sitz + 1];
        if (sitz + 1 < passagiers[reihe].length && reihe - 1 >= 0)
            betroffenePersonen[7] = passagiers[reihe - 1][sitz + 1];
        return betroffenePersonen;
    }

    public float calculatePositiveChance() {
        //Get a random float between 0 and 1
        Random rand = new Random();
        float positiveChance = rand.nextFloat();
        System.out.println(positiveChance);
        return positiveChance;
    }

    public boolean positivPassengar() {
        //Chance for a passengar to be positive is 12.5%
        return calculatePositiveChance() > 0.125;
    }
}
