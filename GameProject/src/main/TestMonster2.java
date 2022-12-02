package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TestMonster2 extends Monster {

	public TestMonster2(int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
		this.setImage[0] = new ImageIcon("images/monster1Basic.png");
		this.setImage[1] = new ImageIcon("images/monster1Attack.png");
		this.setImage[2] = new ImageIcon("images/monster1Attacked.png");
		for (int i = 0; i < 3; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(260, 260, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(370, 200, 260, 260);
		}
		this.nowImage = this.images[0];
	}

	@Override
	void attack() {
		int t = (int) (Math.random() * 3 + 1);
		warningPos(t);
		warning.setIcon(color[0]);
	}


}
