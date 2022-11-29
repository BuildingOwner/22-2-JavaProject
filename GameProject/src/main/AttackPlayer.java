package main;

public class AttackPlayer extends Thread {
	Player p;
	Monster m;
	GameFrame gf;

	AttackPlayer(Player p, Monster m, GameFrame gameF) {
		this.p = p;
		this.m = m;
		this.gf = gameF;
	}

	public void run() {
		p.nowImage.setIcon(p.setImage[1]);
		m.hp = m.hp - Math.round((p.damage * (100 - m.armor) / 100.0) * 100) / 100;
		// 공격이 연타가 가능함 공속을 낮추는법 연구 필요
		gf.tgp.repaint();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		p.nowImage.setIcon(p.setImage[0]);
	}
}
