package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	public Scanner sc = new Scanner(System.in);
	public StartPanel startP;
	public HelpPanel helpP;
	public SavePanel saveP;
	public GamePanel gameP;
	public InputName nameP;
	public Audio punch = new Audio("audio/punch.wav", false);

	Player player;
	Monster monster;
	public TestGamePanel tgp;
	FrameCount fc = new FrameCount();
	
	int term = 0;


	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 100, 1000, 700);

		startP = new StartPanel(this);
		helpP = new HelpPanel(this);
		saveP = new SavePanel(this);

		this.add(startP);
		this.setVisible(true);
		
		
		
	}

	public void game(String userName, Item[] items) {
		player = new Player(this, 100, 1, 0, userName, items);
		monster = createMonster();

		tgp = new TestGamePanel(player, monster, items);
		redraw(tgp);
		this.addKeyListener(new MyKeyEvent());
		this.setFocusable(true);
		this.requestFocus();

		fc.start();
		
	}

	void gameRun() {
		while (true) {
			

			
			System.out.printf("frame : %d sync : %d %d motion : %d %d\n", fc.frame, fc.pSync, fc.mSync, fc.pMotion,
					fc.mMotion);

			// 플레이어 기본 공격
			if (fc.pMotion % 10 == 0) { // 공격 모션
				player.nowImage.setIcon(player.setImage[0]);
				fc.pMotion = 1;

			}
			if (fc.pMotion % 5 == 0) {
				monster.nowImage.setIcon(monster.setImage[0]);
			}
			if (fc.pSync % 20 == 0) { // 공격 속도
				player.attack = false;
				fc.pSync += 1;
			}

			// 몬스터 공격,
			if (fc.mSync % 30 == 0) {
				monster.nowImage.setIcon(monster.setImage[1]);
				monster.attack();
				fc.mMotion = 2;
				fc.mSync++;
			}
			if(fc.mMotion % 5 == 0) {
				monster.nowImage.setIcon(monster.setImage[0]);
				player.nowImage.setIcon(player.setImage[0]);
			}
			if (fc.mMotion % 10 == 0) {
				monster.warning.setIcon(monster.color[1]);
				monster.attack = true;
				term = fc.frame;
				fc.mMotion = 1;
			}
			try {
				if (monster.attack) {
					if (player.lp >= monster.warning.getX() && player.lp <= (monster.warning.getX() + 190)
							|| player.rp >= (monster.warning.getX() + 10) && player.rp <= (monster.warning.getX() + 200)) {
						player.nowImage.setIcon(player.setImage[2]);
						fc.pMotion = 2;
						int d = monster.damage;
						d *= monster.paturn;
						player.hp = player.hp - Math.round((d * (100 - player.armor) / 100.0) * 100) / 100;
						tgp.repaint();
						monster.attack = false;
					}
					if (fc.frame == (term + 10)) {
						term = 0;
						monster.attack = false;
					}

				}
			} catch (NullPointerException e) {

			}
		}
	}

	private Monster createMonster() {
		Monster m = null;
		int i = (int) (Math.random() * 4 + 1);
		switch (i) { // 몬스터들 수 만큼 늘어나야 함
		case 1:
			m = new TestMonster(100, 1, 0, "test monster");
			break;
		case 2:
			m = new TestMonster2(100, 1, 0, "test monster2");
			break;
		case 3:
			m = new TestMonster3(100, 1, 0, "test monster3");
			break;
		case 4:
			m = new TestMonster4(100, 1, 0, "test monster4");
			break;
		}
		return m;
	}

	public void redraw(JPanel p) {
		this.getContentPane().removeAll();
		this.add(p);
		this.revalidate();
		this.repaint();
	}

	class MyKeyEvent extends KeyAdapter { // 동시 입력 시 끊김 동시입력 연구 필요
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case 'A':
				player.attack();
				punch.start();
				fc.pMotion++;

				break;
			case KeyEvent.VK_LEFT:
				if (player.nowImage.getX() > 200) {
					player.nowImage.setLocation(player.nowImage.getX() - 20, player.nowImage.getY());
					player.lp = player.nowImage.getX();
					player.rp = player.nowImage.getX() + 140;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (player.nowImage.getX() < 700) {
					player.nowImage.setLocation(player.nowImage.getX() + 20, player.nowImage.getY());
					player.lp = player.nowImage.getX();
					player.rp = player.nowImage.getX() + 140;
				}
				break;
			}
		}
	}

	public static void main(String[] args) {
		GameFrame game = new GameFrame();
		game.gameRun();

	}
}
