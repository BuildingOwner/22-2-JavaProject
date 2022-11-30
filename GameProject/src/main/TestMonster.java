package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TestMonster extends Monster {
	

	public TestMonster(int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
		this.setImage[0] = new ImageIcon("images/monsterBasic.png");
		this.setImage[1] = new ImageIcon("images/monsterAttack.png");
//		this.setImage[2] = new ImageIcon("images/monsterAttacked.png");
		this.setImage[3] = new ImageIcon("");
		for (int i = 0; i < 4; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(140, 210, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(430, 50, 140, 210);
		}
		this.nowImage = this.images[0];
	}

}
