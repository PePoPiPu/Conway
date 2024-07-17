package Conway;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize game and graphical interface
        Conway conway = new Conway();
        conway.initalizeCellsArray();
        GridPanel gridPanel = new GridPanel(conway.getCellsArray());
        GUI gui = new GUI(conway.getCellsArray());

        gui.createAndShowGUI(gridPanel);

        // Create game loop at 60ish FPS
        Timer timer = new Timer(1000/60, e -> {
            // Update game board
            conway.updateGameBoard();
            // Repaint with new instance of the board
            gridPanel.setGrid(conway.getCellsArray());
        });
        timer.start();
    }
}
