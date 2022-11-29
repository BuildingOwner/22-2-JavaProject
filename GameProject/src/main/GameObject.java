package main;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class GameObject {
	double hp;
	int damage; // 평타 데미지
	int armor;
	Point position;
	String name;
	ImageIcon[] setImage = new ImageIcon[4]; 
	JLabel[] images = new JLabel[4]; // 0:기본, 1:공격, 2:피격, 3:없음
	JLabel nowImage;
	public GameObject(int hp, int damage, int armor, String name) {
		this.hp = hp;
		this.damage = damage;
		this.armor = armor;
		this.name = name;
	}

}
