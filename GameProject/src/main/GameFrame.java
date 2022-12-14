package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameFrame extends JFrame {
	public Scanner sc = new Scanner(System.in);
	public StartPanel startP;
	public HelpPanel helpP;
	public SavePanel saveP;
	public InputNamePanel nameP;
	public ItemPanel itemP;
	public GamePanel gameP;
	public JFrame itemF;
	public GameFrame gf = this;
	public ImageIcon[] screenImage = new ImageIcon[12];
	public JLabel[] backgrounds = new JLabel[12];
	
	Player player;
	Monster monster;
	FrameCount fc;

	int term = 0;
	int stage = 1;
	boolean flag = false;
	boolean end = false;

	public GameFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(300, 100, 1000, 700);

		for (int i = 0; i < screenImage.length; i++) {
			screenImage[i] = new ImageIcon("images/stage" + i + "map.png");
			Image tmp = screenImage[i].getImage();
			Image tmp2 = tmp.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
			this.screenImage[i] = new ImageIcon(tmp2);
			backgrounds[i] = new JLabel(screenImage[i]);
			backgrounds[i].setSize(1000, 700);
		}

		startP = new StartPanel(this);
		helpP = new HelpPanel(this);
		itemP = new ItemPanel(this);

		this.add(startP);
		this.setVisible(true);
		redraw(startP);

	}

	public void game(String userName, Item[] items) {
		stage = 1;
		player = new Player(this, 100, 1, 0, "");
		player.name = userName;
		player.items = items;
		for (int i = 0; i < player.items.length; i++) {
			if (player.items[i].name != null) {
				player.itemCnt++;
			}
		}
		monster = createMonster();

		gameP = new GamePanel(this, items);
		gameP.stage += stage;
		gameP.repaint();
		redraw(gameP);
		this.addKeyListener(new MyKeyEvent());
		this.setFocusable(true);
		this.requestFocus();

		fc = new FrameCount();
		fc.start();
		flag = true;
	}

	void gameRun() {
		flag = true;
		while (true) {
			try {
				System.out.println(flag);
				if (flag) {
//					end = true;
					System.out.printf("frame : %d sync : %d %d motion : %d %d\n", fc.frame, fc.pSync, fc.mSync,
							fc.pMotion, fc.mMotion);

					// 플레이어 기본 공격
					if (fc.pMotion % 10 == 0) { // 공격 모션
						player.nowImage.setIcon(player.setImage[0]);
						fc.pMotion = 1;
						player.attack = true;

					}
					if (fc.pMotion % 5 == 0) {
						monster.nowImage.setIcon(monster.setImage[0]);
					}
					if (fc.pSync % 20 == 0) { // 공격 속도
						player.attack = false;
						fc.pSync += 1;
					}

					// 몬스터 공격
					if (fc.mSync % 30 == 0) {
						monster.nowImage.setIcon(monster.setImage[1]);
						monster.attack();
						fc.mMotion = 2;
						fc.mSync++;
					}
					if (fc.mMotion % 5 == 0) {
						monster.nowImage.setIcon(monster.setImage[0]);
						player.nowImage.setIcon(player.setImage[0]);
					}
					if (fc.mMotion % 10 == 0) {
						monster.warning.setIcon(monster.color[1]);
						monster.attack = true;
						term = fc.frame;
						fc.mMotion = 1;
					}

					// 플레이어 피격 판정
					try {
						if (monster.attack) {
							if (player.lp >= monster.warning.getX() && player.lp <= (monster.warning.getX() + 190)
									|| player.rp >= (monster.warning.getX() + 10)
											&& player.rp <= (monster.warning.getX() + 200)) {
								player.nowImage.setIcon(player.setImage[2]);
								fc.pMotion = 2;
								int d = monster.damage;
								d *= monster.paturn;
								player.hp = player.hp - Math.round((d * (100 - player.armor) / 100.0) * 100) / 100;
								gameP.repaint();
								monster.attack = false;
							}
							if (fc.frame == (term + 10)) {
								term = 0;
								monster.attack = false;
							}

						}
					} catch (NullPointerException e) {

					}

					// 스킬 쿨타임
					if (fc.frame % 100 == 0) {
						System.out.println(player.items[0].coolTime);
						fc.frame++;
						for (int i = 0; i < player.itemCnt; i++) {
							if (player.items[i].coolTime == 0) {
								continue;
							}
							player.items[i].coolTime--;
							gameP.repaint();
						}
					}

					// debug : 게임 종료
					if (stage > 10000) {
						end = true;
					}
					// 몬스터 사망시 아이템 획득
					try {
						if (monster.hp <= 0) {
							flag = false;
							fc.interrupt();
							monster.hp = 100;
							itemF = new JFrame();
							itemF.setDefaultCloseOperation(EXIT_ON_CLOSE);
							itemF.setBounds(450, 150, 700, 600);
							itemF.add(new ItemPanel(this));
							itemF.setVisible(true);
						}
					} catch (NullPointerException e) {

					}

					if (player.hp <= 0) {
						end = true;
					}
				}
			} catch (NullPointerException e) {

			}

			// 게임 종료
			if (end) {
				flag = false;
				end = false;
				EndPanel clearP = new EndPanel(this);
				redraw(clearP);
				stage = 1;
			}
		}
	}

	public Monster createMonster() {
		String name = "";
		int i = (int) (Math.random() * 4 + 1); // 몬스터들 수 만큼 늘어나야 함

		switch (i) {
		case 1:
			name = "Vat";
			break;
		case 2:
			name = "Flame Snake";
			break;
		case 3:
			name = "Goblin";
			break;
		case 4:
			name = "Undead";
			break;
		}

		return new Monster(50 * stage, stage * 2, 0, name, i);
	}

	public void redraw(JPanel p) {
		this.getContentPane().removeAll();
		this.add(p);
		this.revalidate();
		this.repaint();
	}

	public void nextStage() {
		stage++;
		player.damage += 4;
		monster = createMonster();
		gameP = new GamePanel(this, player.items);
		gameP.stage = "스테이지" + stage;
		redraw(gameP);
		fc = new FrameCount();
		fc.start();
		player.nowImage.setIcon(player.setImage[0]);
		flag = true;
	}

	public void resumeStage() {
		redraw(gameP);
		fc = new FrameCount();
		fc.start();
		flag = true;
	}

	public static JButton createBtn(String text) {
		JButton button;
		if (text == null) {
			button = new JButton();
		} else {
			button = new JButton(text);
		}
		button.setForeground(Color.white);
		button.setBackground(Color.black);
		button.setFocusPainted(false);
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				button.setBackground(Color.gray);
			}

			public void mouseExited(MouseEvent evt) {
				button.setBackground(Color.black);
			}
		});

		Border line = new LineBorder(Color.white, 3);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		button.setBorder(line);
		return button;
	}

	public static JLabel mkLabel(String text, int n) {
		JLabel tmp = new JLabel(text, SwingConstants.CENTER);
		if (n == 0) {
			Font font = new Font("", Font.BOLD, 15);
			tmp.setFont(font);
			tmp.setForeground(Color.white);
			tmp.setOpaque(true);
			tmp.setBackground(Color.black);
		} else {
			Font font = new Font("", Font.BOLD, n);
			tmp.setFont(font);
			tmp.setForeground(Color.white);
			tmp.setOpaque(true);
			tmp.setBackground(Color.black);
			tmp.setBorder(new LineBorder(Color.white, 3));
		}
		return tmp;
	}

	class MyKeyEvent extends KeyAdapter { // 동시 입력 시 끊김 동시입력 연구 필요
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_ESCAPE:
				flag = false;
				fc.interrupt();
				redraw(new PausePanel(gf));
				break;
			case 'A':
				player.attack();
				fc.pMotion++;
				break;
			case 'Q':
				player.skill(player.items[0]);
				break;
			case 'W':
				player.skill(player.items[1]);
				break;
			case 'E':
				player.skill(player.items[2]);
				break;
			case 'R':
				player.skill(player.items[3]);
				break;
			case KeyEvent.VK_LEFT:
				if (player.nowImage.getX() > 200) {
					player.nowImage.setLocation(player.nowImage.getX() - 20, player.nowImage.getY());
					player.lp = player.nowImage.getX();
					player.rp = player.nowImage.getX() + 140;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (player.nowImage.getX() < 700 && flag == true) {
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
