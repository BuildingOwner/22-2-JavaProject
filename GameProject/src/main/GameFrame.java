package main;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	public Scanner sc = new Scanner(System.in);
	public StartPanel startP;
	public HelpPanel helpP;
	public SavePanel saveP;
	public GamePanel gameP;
	public InputName nameP;

	Player player;
	Monster monster;
	public TestGamePanel tgp;
	FrameCount fc = new FrameCount();

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
			if (fc.pSync % 20 == 0) { // 공격 속도
				player.attack = false;
				fc.pSync += 1;
			}
			if (fc.mMotion % 5 == 0) { // 피격 모션
				monster.nowImage.setIcon(monster.setImage[0]);
				fc.mMotion = 1;
			}
			
			if(fc.mSync%30==0) {
				tgp.repaint();
			}
		}
	}

	private Monster createMonster() {
		Monster m = null;
		int i = (int) Math.random() + 1;
		switch (i) { // 몬스터들 수 만큼 늘어나야 함
		case 1:
			m = new TestMonster(100, 1, 0, "test monster"); // 각자 몬스터 클래스 들어갈 예정
		}
		return m;
	}

	public void redraw(JPanel p) {
		this.getContentPane().removeAll();
		this.add(p);
		this.revalidate();
		this.repaint();
	}

	class MyKeyEvent extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case 'A':
				player.attack();
				fc.pMotion++;
				fc.mMotion++;

				break;
			case KeyEvent.VK_LEFT:
				if (player.nowImage.getX() > 200) {
					player.nowImage.setLocation(player.nowImage.getX() - 20, player.nowImage.getY());
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (player.nowImage.getX() < 700) {
					player.nowImage.setLocation(player.nowImage.getX() + 20, player.nowImage.getY());
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
