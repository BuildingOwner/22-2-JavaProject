package main;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends GameObject {
	public int paturn = 1;
	public JLabel warning;
	public ImageIcon[] color = new ImageIcon[2];
	public Audio Attack = new Audio("audio/monsterAttack.wav", false,-3);
	
	public Monster(int hp, int damage, int armor, String name, int n) {
		super(hp, damage, armor, name);
		// 몬스터 이미지 초기화
		this.setImage[0] = new ImageIcon("images/monster" + n + "Basic.png");
		this.setImage[1] = new ImageIcon("images/monster" + n + "Attack.png");
		this.setImage[2] = new ImageIcon("images/monster" + n + "Attacked.png");
		for (int i = 0; i < 3; i++) {
			Image tmp = this.setImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(340, 340, Image.SCALE_SMOOTH);
			this.setImage[i] = new ImageIcon(tmp2);
			this.images[i] = new JLabel(this.setImage[i]);
			this.images[i].setBounds(330, 200, 340, 340);
		}
		this.nowImage = this.images[0];

		// 몬스터 공격 경고 이미지 초기화
		color[0] = new ImageIcon("images/warning.png");
		color[1] = new ImageIcon("images/rmWarning.png");
		for (int i = 0; i < 2; i++) {
			Image tmp = color[i].getImage();
			Image tmp2 = tmp.getScaledInstance(200, 700, Image.SCALE_SMOOTH);
			color[i] = new ImageIcon(tmp2);
		}
		warning = new JLabel(color[1]);
		warning.setBounds(150, 0, 200, 700);
	}

	@Override
	void attack() {
		Attack.start();
		int t = (int) (Math.random() * 3 + 1);
		warningPos(t);
		warning.setIcon(color[0]);
	}

	public void warningPos(int n) {
		switch (n) {
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

}
