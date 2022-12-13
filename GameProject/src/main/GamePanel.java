package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	GameFrame gf;
	public JLabel[] cl = new JLabel[4];
	private JLabel[] skillKey = new JLabel[4];
	public static Audio mainbg = new Audio("audio/mainbg.wav", true,-10);
	
	String stage = "스테이지";
	String playerName = "나";
	String monsterName = "몬스터";

	Image[] playerItem = new Image[4];

	public GamePanel(GameFrame gf, Item[] items) {
		mainbg.start();
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
		int n = (int) (Math.random() * 5 + 1);
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

	public void healthDraw(Graphics g) {
		g.setColor(Color.white);
		drawThickRect(g, 18, 18, 354, 44, 4, false);

		g.setColor(Color.black);
		g.fillRect(20, 20, 350, 40);
		g.setColor(Color.RED);
		g.fillRect(20, 20, (int) (350 * (gf.player.hp / 100)), 40); // Player 남은 체력 칸

		g.setColor(Color.white);
		drawThickRect(g, 613, 18, 354, 44, 4, false);

		g.setColor(Color.black);
		g.fillRect(615, 20, 350, 40);
		g.setColor(Color.RED);
		g.fillRect(615 + (350 - (int) (350 * (gf.monster.hp / (50 * gf.stage)))), 20,
				(int) (350 * (gf.monster.hp / (50 * gf.stage))), 40); // Monster 남은 체력 칸
	}

	public void itemDraw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(20, 580, 280, 70);
		g.setColor(Color.white);
		for (int i = 0; i < 4; i++) {
			drawThickRect(g, 20 + (i * 70), 580, 70, 70, 3, false);
			g.drawImage(gf.player.items[i].imageItem, 20 + (i * 70), 580, 70, 70, this);
		}
	}

	public void infoDraw(Graphics g) { // 스테이지, 플레이어, 몬스터 이름 출력
		g.setColor(Color.black);
		g.fillRoundRect(420 - ((stage.length() % 5) * 10), 20, 30 * stage.length() - ((stage.length() % 5) * 13), 40, 5,
				5);
		g.setColor(Color.white);
		drawThickRect(g, 420 - ((stage.length() % 5) * 10), 20, 30 * stage.length() - ((stage.length() % 5) * 13), 40,
				4, true);
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString(stage, 425 - ((stage.length() % 5) * 10), 50);
		g.setFont(new Font("", Font.BOLD, 20));
		drawThickRect(g, 20, 80, 13 * playerName.length(), 30, 4, true);
		drawThickRect(g, 940 - (monsterName.length() * 10), 80, 13 * monsterName.length(), 30, 4, true);
		g.setColor(Color.black);
		g.fillRoundRect(20, 80, 13 * playerName.length(), 30, 5, 5);
		g.fillRoundRect(940 - (monsterName.length() * 10), 80, 13 * monsterName.length(), 30, 5, 5);
		g.setColor(Color.white);
		g.drawString(playerName, 22, 100);
		g.drawString(monsterName, 942 - (monsterName.length() * 10), 100);

	}

	public void drawThickRect(Graphics g, int x, int y, int width, int height, int thick, boolean round) {
		if (round) {
			Graphics2D g2 = (Graphics2D) g;
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(thick));
			g2.drawRoundRect(x, y, width, height, 5, 5);
			g2.setStroke(oldStroke);
		} else {
			Graphics2D g2 = (Graphics2D) g;
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(thick));
			g2.drawRect(x, y, width, height);
			g2.setStroke(oldStroke);
		}
	}

	public void drawKey(Graphics g) {
		String[] keys = { " Q", " W", " E", " R" };
		g.setFont(new Font("", Font.BOLD, 15));
		for (int i = 0; i < gf.player.items.length; i++) {
			g.drawString(keys[i], 23 + (70 * i), 640);
			g.drawString(Integer.toString(gf.player.items[i].coolTime), 70 + (70 * i), 600);
		}
	}
}
