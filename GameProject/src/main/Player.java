package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends GameObject {
	Item[] items = new Item[4];

	public Player(int hp, int damage, int armor, String name, Item[] items) {
		super(hp, damage, armor, name);
		this.items = items;
		
		this.setImage[0] = new ImageIcon("images/playerBasic.png");
		this.setImage[1] = new ImageIcon("images/playerAttack.png");
		this.setImage[2] = new ImageIcon("images/playerAttacked.png");
		this.setImage[3] = new ImageIcon("");
		for (int i = 0; i < 4; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(140, 210, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(430, 400, 140, 210);
		}
		this.nowImage = this.images[0];
		
		
	}


}
