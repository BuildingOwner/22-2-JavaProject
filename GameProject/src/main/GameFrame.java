package main;

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

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 100, 1000, 700);

		startP = new StartPanel(this);
		helpP = new HelpPanel(this);
		saveP = new SavePanel(this);

		this.add(startP);
		this.setVisible(true);
	}

	public void run(String userName, Item[] items) {

		Player p = new Player(100, 1, 0, userName, items);
		player = p;
		Monster m = null;
		m = createMonster();

		gameP = new GamePanel(this, userName, items);
		redraw(gameP);
		// 스래드로 몬스터의 공격과 플레이어의 공격, 게임 프레임의 게임 진행을 스레드로 구현 해야 함
		this.addKeyListener(new MyKeyEvent());
	}

	private Monster createMonster() {
		Monster m = null;
		int i = (int) Math.random() + 1;
		switch (i) { // 몬스터들 수 만큼 늘어나야 함
		case 1:
			m = new Monster(100, 1, 0, "test monster"); // 각자 몬스터 클래스 들어갈 예정
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
			case KeyEvent.VK_UP:
				gameP.playerDraw(getGraphics(), , keyCode);
			}
		}
	}

	public static void main(String[] args) {
		GameFrame game = new GameFrame();

	}
}
