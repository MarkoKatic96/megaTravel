package app.main;

import java.awt.EventQueue;

import gui.LoginGUI;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.getMainFrame().setVisible(true);
					
					//MainGUI m = new MainGUI();
					//m.getMainFrame().setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
