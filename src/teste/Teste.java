package teste;

import java.awt.*;

import control.ControlePrincipal;

public class Teste{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlePrincipal run = new ControlePrincipal();
					run.startApp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
