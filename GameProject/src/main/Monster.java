package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Monster extends GameObject {
	public int paturn;
	public JLabel warning;

	public Monster(int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
		warning = new JLabel(new ImageIcon("image/warning.png"));
	}

	abstract void attack();

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
