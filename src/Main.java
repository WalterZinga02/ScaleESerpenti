import game.Caretaker;
import game.GameManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Caretaker caretaker = new Caretaker();
        GameManager gameManager = null;

        System.out.println("Benvenuto nel gestore di configurazioni del gioco!");
        System.out.println("Scegli un'opzione:");
        System.out.println("1. Creare un nuovo gioco");
        System.out.println("2. Caricare una configurazione esistente");

        int scelta = scanner.nextInt();
        scanner.nextLine(); // Consuma il carattere newline

        switch (scelta) {
            case 1:
                // Creazione di un nuovo gioco
                System.out.println("Creazione di un nuovo gioco.");
                System.out.print("Inserisci il numero di giocatori (2-4): ");
                int players = scanner.nextInt();

                System.out.print("Inserisci il numero di righe: ");
                int rows = scanner.nextInt();

                System.out.print("Inserisci il numero di colonne: ");
                int columns = scanner.nextInt();

                System.out.print("Vuoi attivare le stopBoxes? (true/false): ");
                boolean stopBoxes = scanner.nextBoolean();

                System.out.print("Vuoi attivare le bonusBoxes? (true/false): ");
                boolean bonusBoxes = scanner.nextBoolean();

                System.out.print("Vuoi attivare le dacBoxes? (true/false): ");
                boolean dacBoxes = scanner.nextBoolean();

                System.out.print("Vuoi utilizzare due dadi? (true/false): ");
                boolean twoDice = scanner.nextBoolean();

                System.out.print("Vuoi attivare il doubleSix? (true/false): ");
                boolean doubleSix = scanner.nextBoolean();

                System.out.print("Vuoi utilizzare la modalità due dadi modificata? (true/false): ");
                boolean twoDiceMod = scanner.nextBoolean();

                // Inizializzazione del GameManager
                gameManager = GameManager.getInstance(players, rows, columns, stopBoxes, bonusBoxes, dacBoxes, twoDice, doubleSix, twoDiceMod);

                try {
                    caretaker.save(gameManager);
                    System.out.println("Nuova configurazione salvata con successo.");
                } catch (IOException e) {
                    System.out.println("Errore durante il salvataggio della configurazione: " + e.getMessage());
                }

                break;

            case 2:
                // Caricamento di una configurazione esistente
                System.out.println("Caricamento dell'ultima configurazione salvata...");

                try {
                    // Tentativo di ripristino dell'ultima configurazione salvata
                    gameManager = GameManager.getInstance(2, 5, 5, false, false, false, false, false, false);
                    caretaker.undo(gameManager);
                    System.out.println("Configurazione caricata con successo.");
                    gameManager.startGame();
                } catch (Exception e) {
                    System.out.println("Errore durante il caricamento della configurazione: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Scelta non valida. Riprova.");
        }

        scanner.close();
    }
}
