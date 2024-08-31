package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class GameSettings {
    private int players;
    private int rows;
    private int columns;
    private boolean stopBoxes;
    private boolean bonusBoxes;
    private boolean dacBoxes;
    private boolean twoDice;
    private boolean doubleSix;
    private boolean twoDiceMod;

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean isStopBoxes() {
        return stopBoxes;
    }

    public void setStopBoxes(boolean stopBoxes) {
        this.stopBoxes = stopBoxes;
    }

    public boolean isBonusBoxes() {
        return bonusBoxes;
    }

    public void setBonusBoxes(boolean bonusBoxes) {
        this.bonusBoxes = bonusBoxes;
    }

    public boolean isDacBoxes() {
        return dacBoxes;
    }

    public void setDacBoxes(boolean dacBoxes) {
        this.dacBoxes = dacBoxes;
    }

    public boolean isTwoDice() {
        return twoDice;
    }

    public void setTwoDice(boolean twoDice) {
        this.twoDice = twoDice;
    }

    public boolean isDoubleSix() {
        return doubleSix;
    }

    public void setDoubleSix(boolean doubleSix) {
        this.doubleSix = doubleSix;
    }

    public boolean isTwoDiceMod() {
        return twoDiceMod;
    }

    public void setTwoDiceMod(boolean twoDiceMod) {
        this.twoDiceMod = twoDiceMod;
    }

    public void saveToFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(Constants.SETTINGS_PATH)) {
            fileWriter.write("players=" + players + "\n");
            fileWriter.write("rows=" + rows + "\n");
            fileWriter.write("columns=" + columns + "\n");
            fileWriter.write("stopBoxes=" + stopBoxes + "\n");
            fileWriter.write("bonusBoxes=" + bonusBoxes + "\n");
            fileWriter.write("dacBoxes=" + dacBoxes + "\n");
            fileWriter.write("twoDice=" + twoDice + "\n");
            fileWriter.write("doubleSix=" + doubleSix + "\n");
            fileWriter.write("twoDiceMod=" + twoDiceMod + "\n");
        }
    }

    public void loadFromFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.SETTINGS_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    switch (key) {
                        case "players":
                            players = Integer.parseInt(value);
                            break;
                        case "rows":
                            rows = Integer.parseInt(value);
                            break;
                        case "columns":
                            columns = Integer.parseInt(value);
                            break;
                        case "stopBoxes":
                            stopBoxes = Boolean.parseBoolean(value);
                            break;
                        case "bonusBoxes":
                            bonusBoxes = Boolean.parseBoolean(value);
                            break;
                        case "dacBoxes":
                            dacBoxes = Boolean.parseBoolean(value);
                            break;
                        case "twoDice":
                            twoDice = Boolean.parseBoolean(value);
                            break;
                        case "doubleSix":
                            doubleSix = Boolean.parseBoolean(value);
                            break;
                        case "twoDiceMod":
                            twoDiceMod = Boolean.parseBoolean(value);
                            break;
                    }
                }
            }
        }
    }

    //method used for the generation of a new simulation
    public GameSettings createNewSettings(int players, int rows, int columns, boolean stopBoxes, boolean bonusBoxes, boolean dacBoxes, boolean twoDice, boolean doubleSix, boolean twoDiceMod) {
        GameSettings settings = new GameSettings();
        settings.setPlayers(players);
        settings.setRows(rows);
        settings.setColumns(columns);
        settings.setStopBoxes(stopBoxes);
        settings.setBonusBoxes(bonusBoxes);
        settings.setDacBoxes(dacBoxes);
        settings.setTwoDice(twoDice);
        settings.setDoubleSix(doubleSix);
        settings.setTwoDiceMod(twoDiceMod);
        return settings;
    }

    public static class Constants {
        public static final String SETTINGS_PATH = "settings.json";
    }
}