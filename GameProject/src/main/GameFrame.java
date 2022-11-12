package main;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	private JPanel layoutP;
	private StartPanel startP;
	
	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 100, 1000, 700);
		
		startP = new StartPanel();
		this.add(startP);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		GameFrame game = new GameFrame();

	}

}
