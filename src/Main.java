import javax.swing.JFrame;

/**
 * Klasa testuj�ca
 */
public class Main {
    public static void main(String[] args){
        //TUI tui = new TUI();
        //tui.start();
        GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
    }
}
