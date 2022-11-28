package main;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GameObject {
	double hp;
	int damage; // 평타 데미지
	int armor;
	Point position;
	int status = 0; // 0이면 일반, 1이면 가드
	String name;
	ImageIcon image;
	JLabel obj;

	public GameObject(int hp, int damage, int armor, String name) {
		this.hp = hp;
		this.damage = damage;
		this.armor = armor;
		this.name = name;
	}

	public abstract void attack(GameObject m);
	

	public void guard() {
		this.status = 1;
	}
}
