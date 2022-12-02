package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TestGamePanel extends JPanel {
	Player p;
	Monster m;

	String stage = "1스테이지";
	String playerName = "나";
	String monsterName = "몬스터";

	Image[] playerItem = new Image[4];

	private Image[] screenImage = { // 배경으로 쓰일 이미지
			new ImageIcon("images/stage1map.png").getImage(), new ImageIcon("images/stage2map.jpg").getImage(),
			new ImageIcon("images/stage3map.jpg").getImage() };
	JLabel mapImage = new JLabel(new ImageIcon(screenImage[0])); // 현재 맵 이미지

	public TestGamePanel(Player player, Monster monster, Item[] items) {
		this.setLayout(null);
		this.p = player;
		this.m = monster;
		this.playerName = player.name;
		this.monsterName = monster.name;
		for (int i = 0; i < 4; i++) {
			this.playerItem[i] = new ImageIcon("images/" + items[i].name + ".png").getImage();
		}
		mapImage.setSize(1000, 700);

		this.add(p.nowImage);
		this.add(m.nowImage);
		this.add(mapImage);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		healthDraw(g);
		itemDraw(g);
		infoDraw(g);
//		if(m.)
	}

	public void healthDraw(Graphics g) { // Player와 Monster의 체력을 받아와야됨.
		g.setColor(Color.RED);
		g.drawRect(20, 20, 350, 40); // Player 최대 체력 칸
		g.fillRect(20, 20, (int) (350 * (p.hp / 100)), 40); // Player 남은 체력 칸
		g.drawRect(615, 20, 350, 40); // Monster 최대 체력 칸
		g.fillRect(615 + (350 - (int)(350*(m.hp/100))), 20, (int)(350*(m.hp/100)), 40); // Monster 남은 체력 칸
		// 위에 이거 왼쪽에서부터 없어지는 법 연구 필요
	}

	public void itemDraw(Graphics g) { // Player가 가진 현재 아이템을 배열에 넣어 출력할 예정
		g.setColor(Color.white);
		for (int i = 0; i < 4; i++) {
			g.drawRect(20 + (i * 70), 580, 70, 70);
			g.drawImage(playerItem[i], 20 + (i * 70), 580, 70, 70, this);
		}
	}

	public void infoDraw(Graphics g) { // 스테이지, 플레이어, 몬스터 이름 출력
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString(stage, 450, 50);
		g.drawString(playerName, 20, 80);
		g.drawString(monsterName, 965-(monsterName.length()*10), 80); // 절대 위치 말고 왼쪽정렬 필요

	}

	public void warning1(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(40, 0, 100, 700);
		g.fillRect(40, 0, 100, 700);
	}

}
