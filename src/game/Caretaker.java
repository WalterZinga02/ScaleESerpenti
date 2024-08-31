package game;
import java.io.*;

public class Caretaker {
    private final String filePath = "C:\\Users\\asus\\Desktop\\configurations\\memento_0.txt";

    public Caretaker() {
    }

    public void save(GameManager gameManager) throws IOException {
        GameSettings settings = gameManager.save();
        saveToFile(settings, filePath);
    }

    public void undo(GameManager gameManager) {
        gameManager.restore(loadFromFile(filePath));

    }

    private void saveToFile(GameSettings memento, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(memento.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GameSettings loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Inizio lettura del file");
            StringBuilder sb = new StringBuilder();
            String line;

            // Legge ogni riga e la aggiunge allo StringBuilder
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Stampa la riga per debug
                sb.append(line);
                sb.append("\n");
            }

            // Crea il testo finale da passare al costruttore di GameSettings
            String text = sb.toString();
            return new GameSettings(text);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

