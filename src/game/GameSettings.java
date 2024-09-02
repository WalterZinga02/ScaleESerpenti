package game;

import java.io.*;
import java.util.StringTokenizer;

public class GameSettings implements Serializable {
    private int players;
    private int rows;
    private int columns;
    private boolean stopBoxes;
    private boolean bonusBoxes;
    private boolean dacBoxes;
    private boolean twoDice;
    private boolean doubleSix;
    private boolean twoDiceMod;

    private static final long serialVersionUID = 0;
    
    public GameSettings(int players, int rows, int columns, boolean stopBoxes, boolean bonusBoxes, boolean dacBoxes, boolean twoDice, boolean doubleSix, boolean twoDiceMod) {
        this.players = players;
        this.rows = rows;
        this.columns = columns;
        this.stopBoxes = stopBoxes;
        this.bonusBoxes = bonusBoxes;
        this.dacBoxes = dacBoxes;
        this.twoDice = twoDice;
        this.doubleSix = doubleSix;
        this.twoDiceMod = twoDiceMod;
    }

    public int getPlayers() {
        return players;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isStopBoxes() {
        return stopBoxes;
    }

    public boolean isBonusBoxes() {
        return bonusBoxes;
    }

    public boolean isDacBoxes() {
        return dacBoxes;
    }

    public boolean isTwoDice() {
        return twoDice;
    }

    public boolean isDoubleSix() {
        return doubleSix;
    }

    public boolean isTwoDiceMod() {
        return twoDiceMod;
    }

    public String getText(){
        return "players=" + players + "\n" +
                "rows=" + rows + "\n" +
                "columns=" + columns + "\n" +
                "stopBoxes=" + stopBoxes + "\n" +
                "bonusBoxes=" + bonusBoxes + "\n" +
                "dacBoxes=" + dacBoxes + "\n" +
                "twoDice=" + twoDice + "\n" +
                "doubleSix=" + doubleSix + "\n" +
                "twoDiceMod=" + twoDiceMod + "\n";
    }

    public GameSettings(String text){
        StringTokenizer st = new StringTokenizer(text, "\n");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            String[] keyValue = token.split("=");
            switch (keyValue[0]) {
                case "players":
                    players = Integer.parseInt(keyValue[1]);
                    break;
                case "rows":
                    rows = Integer.parseInt(keyValue[1]);
                    break;
                case "columns":
                    columns = Integer.parseInt(keyValue[1]);
                    break;
                case "stopBoxes":
                    stopBoxes = Boolean.parseBoolean(keyValue[1]);
                    break;
                case "bonusBoxes":
                    bonusBoxes = Boolean.parseBoolean(keyValue[1]);
                    break;
                case "dacBoxes":
                    dacBoxes = Boolean.parseBoolean(keyValue[1]);
                    break;
                case "twoDice":
                    twoDice = Boolean.parseBoolean(keyValue[1]);
                    break;
                case "doubleSix":
                    doubleSix = Boolean.parseBoolean(keyValue[1]);
                    break;
                case "twoDiceMod":
                    twoDiceMod = Boolean.parseBoolean(keyValue[1]);
                    break;
            }
        }
    }
}