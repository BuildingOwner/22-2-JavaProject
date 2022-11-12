package main;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame gameF = this;
	public StartPanel startP;
	public HelpPanel helpP;
	public SavePanel saveP;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 100, 1000, 700);

		startP = new StartPanel(gameF);
		helpP = new HelpPanel(gameF);
		saveP = new SavePanel(gameF);
		
		
		this.add(startP);
		this.setVisible(true);

	}


	public static void main(String[] args) {
		GameFrame game = new GameFrame();

	}

}
