package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	GameFrame gf;
	public JLabel[] cl = new JLabel[4];
	private JLabel[] skillKey = new JLabel[4];

	String stage = "스테이지";
	String playerName = "나";
	String monsterName = "몬스터";

	Image[] playerItem = new Image[4];

	public GamePanel(GameFrame gf, Item[] items) {
		this.setLayout(null);
		this.gf = gf;
		this.playerName = gf.player.name;
		this.monsterName = gf.monster.name;

		for (int i = 0; i < 4; i++) {
			this.playerItem[i] = new ImageIcon("images/" + items[i].name + ".png").getImage();
		}
		
		this.add(gf.player.nowImage);
		this.add(gf.monster.nowImage);
		this.add(gf.monster.warning);
		int n = (int)(Math.random()*5+1);
		this.add(gf.backgrounds[n]);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		healthDraw(g);
		itemDraw(g);
		infoDraw(g);
		drawKey(g);
	}

	public void healthDraw(Graphics g) { // Player와 Monster의 체력을 받아와야됨.
		g.setColor(Color.RED);
		g.drawRect(20, 20, 350, 40); // Player 최대 체력 칸
		g.fillRect(20, 20, (int) (350 * (gf.player.hp / 100)), 40); // Player 남은 체력 칸
		g.drawRect(615, 20, 350, 40); // Monster 최대 체력 칸
		g.fillRect(615 + (350 - (int) (350 * (gf.monster.hp / (50 * gf.stage)))), 20,
				(int) (350 * (gf.monster.hp / (50 * gf.stage))), 40); // Monster 남은 체력 칸
	}

	public void itemDraw(Graphics g) { // Player가 가진 현재 아이템을 배열에 넣어 출력할 예정
		g.setColor(Color.black);
		g.fillRect(20, 580, 280, 70);
		g.setColor(Color.white);
		for (int i = 0; i < 4; i++) {
			g.drawRect(20 + (i * 70), 580, 70, 70);
			g.drawImage(gf.player.items[i].imageItem, 20 + (i * 70), 580, 70, 70, this);
		}
	}

	public void infoDraw(Graphics g) { // 스테이지, 플레이어, 몬스터 이름 출력
		g.setColor(Color.black);
		g.fillRoundRect(420, 20, 158, 40, 5, 5);
		g.setColor(Color.white);
		g.drawRoundRect(420, 20, 158, 40, 5, 5);
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString(stage, 430, 50);
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString(playerName, 20, 80);
		g.drawString(monsterName, 965 - (monsterName.length() * 10), 80);

	}

	public void drawKey(Graphics g) {
		String[] keys = { " Q", " W", " E", " R" };
		g.setFont(new Font("", Font.BOLD, 15));
		for(int i=0; i<gf.player.items.length; i++) {
			g.drawString(keys[i], 23 + (70 * i), 640);
			g.drawString(Integer.toString(gf.player.items[i].coolTime), 70 + (70 * i), 600);
		}
	}

}
