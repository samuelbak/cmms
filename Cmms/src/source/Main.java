package source;

import gui.MainForm;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		if(!DatabaseSetup.checkRegistry())
			DatabaseSetup.writeConfiguration();
		else
			DatabaseSetup.readConfiguration();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm mainForm = new MainForm();
					mainForm.setLocationRelativeTo(null);
					mainForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
