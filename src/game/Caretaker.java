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
            System.out.println("Reading from file...");
            StringBuilder sb = new StringBuilder();
            String line;

            // reads every line
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // debug
                sb.append(line);
                sb.append("\n");
            }

            // creates the new text to buid the game settings
            String text = sb.toString();
            return new GameSettings(text);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
