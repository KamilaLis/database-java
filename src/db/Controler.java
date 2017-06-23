package db;

public class Controler {
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Controller controller = new Controller();
            	Viewer viewer = new Viewer(controller);
                viewer.createAndShowGUI();
            }
        });
	}
}
