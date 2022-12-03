package main;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends GameObject {
	public int paturn = 1;
	public JLabel warning;
	public ImageIcon[] color = new ImageIcon[2];
	

	public Monster(int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
		color[0] = new ImageIcon("images/warning.png");
		color[1] = new ImageIcon("images/rmWarning.png");
		for(int i=0; i<2; i++) {
			Image tmp = color[i].getImage();
			Image tmp2 = tmp.getScaledInstance(200, 700, Image.SCALE_SMOOTH);
			color[i] = new ImageIcon(tmp2);
		}
		warning = new JLabel(color[1]);
		warning.setBounds(150, 0, 200, 700);
	}

	@Override
	void attack() {
		int t = (int) (Math.random() * 3 + 1);
		warningPos(t);
		warning.setIcon(color[0]);
	}

	public void warningPos(int n) {
		switch(n) {
		case 1:
			warning.setBounds(150, 0, 200, 700);
			break;
		case 2:
			warning.setBounds(350, 0, 200, 700);
			break;
		case 3:
			warning.setBounds(550, 0, 200, 700);
			break;
		}
		
	}

//	public void attack(GameObject m) {
//		int d = this.damage;
//		d *= paturn;
//		m.hp = m.hp - Math.round((d * (100 - m.armor) / 100.0) * 100) / 100;
//	}

}
