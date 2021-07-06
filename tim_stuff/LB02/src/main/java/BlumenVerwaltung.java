import java.util.ArrayList;

public class BlumenVerwaltung {
    private final int maxBlumen;
    private ArrayList<Blume> blumen = new ArrayList<>();
    private int idCounter = 1;
    private final Einleser scanner = new Einleser();

    public BlumenVerwaltung(int maxBlumen) {
        this.maxBlumen = maxBlumen;
        run();
    }

    private void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println(getMenu());
            System.out.print("> ");
            char c = scanner.readChar();
            Command command = Command.getByShortcut(c);
            while (command == null) {
                System.out.println("Geben Sie einen der angegebenen Buchstaben an!");
                System.out.print("> ");
                c = scanner.readChar();
                command = Command.getByShortcut(c);
            }

            switch (command) {
                case CREATE_FLOWER:
                    createFlower();
                    break;

                case PRINT_INFOS:
                    System.out.println("Es werden " + blumen.size() + " Blumen angezeigt...");
                    for(Blume blume : blumen) {
                        System.out.println(blume);
                    }
                    System.out.println();
                    break;

                case EXIT:
                    exit = true;
                    break;
            }
        }
        System.out.println("Auf wiedersehen!");
    }

    private String getMenu() {
        StringBuilder menu = new StringBuilder();
        for (Command command : Command.getValues()) {
            menu.append(command.getDescription()).append("\n");
        }
        return menu.toString();
    }

    private void createFlower() {
        System.out.println("Hinzufuegen einer Blume!");

        System.out.print("Name der Blume > ");
        String name = scanner.readString();

        System.out.print("Preis der Blume > ");
        double preis = scanner.readDouble();

        System.out.print("Farbe der Blume > ");
        String farbe = scanner.readString();

        System.out.print("Ursprungsland der Blume > ");
        String ursprungsland = scanner.readString();

        blumen.add(new Blume(idCounter++, name, preis, farbe, ursprungsland));
        System.out.println("Blume wurde erfolgreich hinzugefügt!");
        System.out.println();
    }

    public int getMaxBlumen() {
        return maxBlumen;
    }

    public ArrayList<Blume> getBlumen() {
        return blumen;
    }

    public void setBlumen(ArrayList<Blume> blumen) {
        this.blumen = blumen;
    }
}