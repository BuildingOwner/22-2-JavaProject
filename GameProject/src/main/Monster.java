package main;

public class Monster extends GameObject {
	public int paturn;

	public Monster(int hp, int damage, int armor, String name) {
		super(hp, damage, armor, name);
	}

	public void attack(GameObject m) {
		int d = this.damage;
		d *= paturn;
		m.hp = m.hp - Math.round((d * (100 - m.armor) / 100.0) * 100) / 100;
	}

}
