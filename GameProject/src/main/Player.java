package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends GameObject {
	Item[] items = new Item[4];

	public Player(int hp, int damage, int armor, String name, Item[] items) {
		super(hp, damage, armor, name);
		this.items = items;
		image = new ImageIcon("./Images/P_dummy.png");
		obj = new JLabel(image);
		obj.setSize(100, 100);
	}

	@Override
	public void attack(GameObject m) {
		m.hp = m.hp - Math.round((this.damage * (100 - m.armor) / 100.0) * 100) / 100;
	}
}
