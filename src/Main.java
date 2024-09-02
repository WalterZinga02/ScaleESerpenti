import gui.GameSettingsGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //starts the settings GUI
        SwingUtilities.invokeLater(() -> {
            GameSettingsGUI gui = new GameSettingsGUI();
            gui.setVisible(true);
        });
    }
}
