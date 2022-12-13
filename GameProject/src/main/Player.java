package main;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends GameObject {
	Item[] items = new Item[4];
	int itemCnt = 0;
	GameFrame gf;
	public Audio punch = new Audio("audio/punch.wav", false,-2);
	public Audio skill = new Audio("audio/sword.wav", false,-6);
	public Audio skill2 = new Audio("audio/staff.wav", false,-5);

	int lp;
	int rp;

	public Player(GameFrame gf, int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
		this.gf = gf;

		this.setImage[0] = new ImageIcon("images/playerBasic.png");
		this.setImage[1] = new ImageIcon("images/playerAttack.png");
		this.setImage[2] = new ImageIcon("images/playerAttacked.png");
		for (int i = 0; i < 3; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(195, 270, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(402, 395, 195, 270);
		}
		this.nowImage = this.images[0];
		lp = nowImage.getX();
		rp = nowImage.getX() + 140;
	}

	@Override
	void attack() {
		if (gf.player.attack) {
			return;
		}
		punch.start();
		gf.player.nowImage.setIcon(gf.player.setImage[1]);
		gf.monster.hp = gf.monster.hp - Math.round((gf.player.damage * (100 - gf.monster.armor) / 100.0) * 100) / 100;
		gf.monster.nowImage.setIcon(gf.monster.setImage[2]);
		gf.player.attack = true;
		gf.gameP.repaint();

		// debug : 한대만 쳐도 몬스터 죽음
		gf.monster.hp = 0;
	}

	void skill(Item a) {
		if (a.name == null || gf.player.attack || a.coolTime > 0) {
			return;
		}
		if (a.name.equals("검(불)") || a.name.equals("검(번개)")) skill.start();
		else skill2.start();
		a.coolTime = a.remain;
		gf.player.nowImage.setIcon(gf.player.setImage[1]);
		gf.monster.hp = gf.monster.hp - a.power;
		gf.monster.nowImage.setIcon(gf.monster.setImage[2]);
		gf.player.attack = true;
		gf.gameP.repaint();
	}

}
