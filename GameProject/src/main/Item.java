package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item {
	public String name;
	public int power;
	public int remain;
	public int coolTime = 0;
	public JLabel image;
	public Image imageItem;
	public int amount;
	public int type; // 0:공격, 1:방어, 2:포션

	public Item(Item t, int power) {
		this.name = t.name;
		this.power = power;
		this.remain = t.remain;
		this.type = 0;
		this.setImage();
	}

	public Item(Item t, int amount, int type) {
		this.name = t.name;
		this.amount = amount;
		this.type = type;
		this.setImage();
	}

	public void setImage() {
		ImageIcon itemImage = new ImageIcon("images/" + name + ".png");
		Image tmp = itemImage.getImage();
		imageItem = tmp.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		itemImage = new ImageIcon(imageItem);
		image = new JLabel(itemImage);
	}

	public Item() {

	}
}
