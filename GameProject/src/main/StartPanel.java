package main;

import java.awt.*;
import javax.swing.*;

public class StartPanel extends JPanel{
	private JLabel title;
	private JButton[] button = new JButton[3];
	
	public StartPanel() {
		title = new JLabel("격투 게임");
		title.setBounds(390, 100, 230, 100);
		Font font = new Font("",Font.BOLD, 50);
		title.setFont(font);
		
		button[0] = new JButton("새 게임");
		button[0].setBounds(425, 330, 150, 50);
		button[1] = new JButton("이어 하기");
		button[1].setBounds(425, 430, 150, 50);
		button[2] = new JButton("도움말");
		button[2].setBounds(425, 530, 150, 50);
		
		this.setLayout(null);
		this.add(title);
		for(int i=0; i<3; i++) {
			this.add(button[i]);
		}
	}
}
