package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends GameObject {
	Item[] items = new Item[4];
	GameFrame gf;

	public Player(GameFrame gf, int hp, int damage, int armor, String name, Item[] items) {
		super(hp, damage, armor, name);
		this.gf = gf;
		this.items = items;

		this.setImage[0] = new ImageIcon("images/playerBasic.png");
		this.setImage[1] = new ImageIcon("images/playerAttack.png");
		this.setImage[2] = new ImageIcon("images/playerAttacked.png");
		for (int i = 0; i < 3; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(140, 210, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(430, 400, 140, 210);
		}
		this.nowImage = this.images[0];

	}

	@Override
	void attack() {
		if (gf.player.attack)
			return;
		gf.player.nowImage.setIcon(gf.player.setImage[1]);
		gf.monster.hp = gf.monster.hp - Math.round((gf.player.damage * (100 - gf.monster.armor) / 100.0) * 100) / 100;
		gf.monster.nowImage.setIcon(gf.monster.setImage[2]);
		gf.player.attack = true;
		gf.tgp.repaint();
	}

}
