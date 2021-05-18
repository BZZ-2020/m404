package Bankkonto;

import java.util.Random;
import java.util.Scanner;

public class Bank {
    Scanner scanner = new Scanner(System.in);
    public Bank() {
        run();
    }

    public static void main(String[] args) {
        new Bank();
    }

    public void run() {
        int amountKonten = 5;
        int amountKunden = amountKonten;

        String[] vornamen = {"Phillip", "Tim", "Marc", "Alyssa", "Magnus", "Moritz"};
        String[] nachnamen = {"Jerebic", "Irmler", "Fuchs", "Heimlicher", "Goetz", "Gasbichler"};
        Kunde[] kunden = new Kunde[amountKunden];
        for(int i=0; i<amountKunden; i++) {
            kunden[i] = new Kunde(i+1, vornamen[getRandomIndex(vornamen.length)], nachnamen[getRandomIndex(nachnamen.length)], ((int) (Math.random() * 100))+1);
        }

        Konto[] konten = new Konto[amountKonten];
        for(int i=0; i<amountKonten; i++) {
            long min = 100L;
            long max = 10000L;
            long amountMoney = min + (long) (Math.random() * (max - min));
            konten[i] = new Konto(i+1,amountMoney, kunden[i]);
        }

        for(Konto konto : konten) {
            System.out.println(konto.toString());
            System.out.println("\n");
        }

        boolean exit = false;
        while(!exit) {
            System.out.println("Welches Konto möchtest du dir ansehen? ");
            for(Konto konto : konten) {
                System.out.println("(" + konto.getId() + ") " + konto.getKontoBesitzer().getVorname() + " " + konto.getKontoBesitzer().getNachname());
            }
            System.out.print("Deine Eingabe > ");
            String input = scanner.nextLine();
            Integer targetBankaccount = getIntFromString(input);
            while(targetBankaccount == null) {
                System.out.print("Bitte eine der angegebenen Zahlen eingeben! > ");
            }
        }
    }

    private int getRandomIndex(int arrayLength) {
        Random r = new Random();
        return r.nextInt(arrayLength);
    }

    public Integer getIntFromString(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Error! Can't convert String to int!");
            return null;
        }
    }
}