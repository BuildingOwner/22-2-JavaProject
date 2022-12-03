package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TestMonster4 extends Monster {
	

	public TestMonster4(int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
		this.setImage[0] = new ImageIcon("images/monster3Basic.png");
		this.setImage[1] = new ImageIcon("images/monster3Attack.png");
		this.setImage[2] = new ImageIcon("images/monster3Attacked.png");
		for (int i = 0; i < 3; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(330, 330, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(335, 190, 330, 330);
		}
		this.nowImage = this.images[0];
	}

}
