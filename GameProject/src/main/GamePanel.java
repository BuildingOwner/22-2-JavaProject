
//package main;

//import javax.swing.JPanel;

//public class GamePanel extends JPanel {
//	public GamePanel(Player p, Monster m) {

//		draw(p, 500, 700);
//	}

//	private void draw(GameObject o, int x, int y) {
//		o.obj.setVisible(false);
//		move(o, x, y);
//		o.obj.setVisible(true);
//	}

//	private void move(GameObject o, int x, int y) {
//		o.obj.setLocation(x, y);
//	}
//}

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class GamePanel extends JPanel {
	String stage = "1스테이지";
	String playerName = "나";
	String monsterName = "몬스터";
	private Image[] player = { // [0]은 기본, [1]은 방어, [2]는 피격, [3]은 공격 시 이미지
			new ImageIcon("images/playerBasic.png").getImage(),
			new ImageIcon("images/A_Armour02.png").getImage(),
			new ImageIcon("images/playerAttacked.png").getImage(),
			new ImageIcon("images/playerAttack.png").getImage()
	};
	private Image[] monster = { // [0]은 기본, [1]은 방어, [2]는 피격, [3] 이상부터는 공격 패턴 이미지
			new ImageIcon("images/monsterBasic.png").getImage(),
			new ImageIcon("images/monsterBasic.png").getImage(),
			new ImageIcon("images/A_Armour03.png").getImage(),
			new ImageIcon("images/monsterAttack.png").getImage()
	};
//	private Image[] item = { // 게임 내 존재하는 아이템 이미지
//			new ImageIcon("images/A_Armour01.png").getImage(),
//			new ImageIcon("images/A_Armour01.png").getImage(),
//			new ImageIcon("images/A_Armour01.png").getImage(),
//			new ImageIcon("images/A_Armour01.png").getImage()
//	};
	private Image[] screenImage = { // 배경으로 쓰일 이미지
			new ImageIcon("images/stage1map.png").getImage(),
			new ImageIcon("images/stage2map.jpg").getImage(),
			new ImageIcon("images/stage3map.jpg").getImage()
	};
	Image mapImage = screenImage[0]; // 현재 맵 이미지
	Image playerAct = player[0]; // 플레이어 현재 이미지
	Image monsterAct = monster[0]; // 몬스터 현재 
	Image[] playerItem = new Image[4];
	public GameFrame gameF;
	
	public GamePanel(GameFrame gameF, String name, Item[] items) {
		this.setLayout(null);
		this.gameF = gameF;
		this.playerName = name;
		for(int i=0; i<4; i++) {
			this.playerItem[i] = new ImageIcon("images/" + items[i].name + ".png").getImage();
		}
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(mapImage, 0, 0, 1000, 700, this);
		infoDraw(g);
		healthDraw(g);
		monsterDraw(g);
		playerDraw(g);
		itemDraw(g);
	}
	
	public void playerDraw(Graphics g) { // 플레이어 현재 이미지를 받아서 그림
		g.drawImage(playerAct, 400, 350 , 200, 350, this);
	}
	
	public void monsterDraw(Graphics g) { // 몬스터 현재 이미지를 받아서 그림
		g.drawImage(monsterAct, 350, 125, 300, 350, this);
	} 
	
	public void healthDraw(Graphics g) { // Player와 Monster의 체력을 받아와야됨.
		g.setColor(Color.RED);
		g.drawRect(20,20, 350, 40); // Player 최대 체력 칸
		g.fillRect(20,20, 350, 40); // Player 남은 체력 칸
		g.drawRect(615, 20, 350, 40); // Monster 최대 체력 칸
		g.fillRect(615, 20, 350, 40); // Monster 남은 체력 칸
	}
	
	public void itemDraw(Graphics g) { // Player가 가진 현재 아이템을 배열에 넣어 출력할 예정
		g.setColor(Color.white);
		for(int i = 0; i <4; i++) {
			g.drawRect(20 + (i*70), 580, 70, 70);
			g.drawImage(playerItem[i], 20 + (i*70), 580, 70, 70, this);
		}
	}
	
	public void infoDraw(Graphics g) { // 스테이지, 플레이어, 몬스터 이름 출력
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString(stage,450,30);
		g.drawString(playerName, 20, 80);
		g.drawString(monsterName, 905, 80);

	}
}

