package main;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GameObject {
	double hp;
	int damage; // 평타 데미지
	int armor;

	String name;
	ImageIcon[] setImage = new ImageIcon[3];
	JLabel[] images = new JLabel[3]; // 0:기본, 1:공격, 2:피격, 3:없음
	JLabel nowImage;
	boolean attack = false;

	public GameObject(int hp, int damage, int armor, String name) {
		this.hp = hp;
		this.damage = damage;
		this.armor = armor;
		this.name = name;
	}

	abstract void attack();
}
