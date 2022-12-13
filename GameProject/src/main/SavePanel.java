package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SavePanel extends JPanel {
	private JLabel title;
	public JButton[] saves = new JButton[3];
	public JButton home;
	public Item[][] items = new Item[3][4];
	public GameFrame gf;
	public String[] userName = new String[3];

	public SavePanel(GameFrame gameF) {
		this.setLayout(null);

		this.gf = gameF;

		title = gf.mkLabel("불러 오기", 50);
		title.setBounds(380, 50, 240, 100);

		for (int i = 0; i < 3; i++) {
			saves[i] = gf.createBtn("");
			saves[i].setBounds(300, 200 + i * 120, 400, 100);
			saves[i].setName("저장소" + (i + 1));
		}

		for (int i = 0; i < 3; i++) {
			JPanel tp = new JPanel();
			tp.setLayout(null);
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader("./SaveFiles/SaveFile" + i + ".txt")));
				if (!sc.hasNext()) {
					throw new IOException();
				}
				userName[i] = sc.next();
				JLabel tl = gf.mkLabel("<< " + userName[i] + " >>", 0);
				tl.setBounds(145, 0, 100, 20);
				tp.add(tl);
				for (int j = 0; j < 4; j++) {
					try {
						items[i][j] = gf.itemP.returnItem(sc.next(), sc.nextInt());
						tl = gf.mkLabel(items[i][j].name + " " + Integer.toString(items[i][j].power), 0);
						tl.setBounds(10 + (j * 95), 30, 90, 20);
						tp.add(tl);
					} catch (NoSuchElementException e) {
						items[i][j] = new Item();
						tl = gf.mkLabel(" 아이템 없음 ", 0);
						tl.setBounds(10 + (j * 95), 30, 90, 20);
						tp.add(tl);
					}

				}
				sc.close();

			} catch (IOException e) {
				for (int j = 0; j < 4; j++) {
					items[i][j] = new Item();
				}
				tp.add(gf.mkLabel("저장된 파일이 없습니다.", 0));
			}
			saves[i].setLayout(null);
			tp.setOpaque(true);
			tp.setBackground(Color.black);
			tp.setBounds(3, 25, 394, 50);
			saves[i].add(tp);
		}

		home = gf.createBtn("홈으로 돌아가기");
		home.setBounds(750, 600, 200, 50);
		HomeBtnAction btn = new HomeBtnAction();
		home.addActionListener(btn);

		this.add(title);
		SaveBtnAction saveBtn = new SaveBtnAction();
		for (int i = 0; i < 3; i++) {
			saves[i].addActionListener(saveBtn);
			this.add(saves[i]);
		}
		this.add(home);
		this.add(gf.backgrounds[8]);

	}

	class HomeBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn.getText().equals("홈으로 돌아가기")) {
				gf.redraw(gf.startP);
				StartPanel.stbg.start();
			}
		}
	}

	class SaveBtnAction implements ActionListener {
		private Modal alert;

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			for (int i = 0; i < 3; i++) {
				if (btn.getName().charAt(3) == (char) (i + 1 + '0')) {
					if (userName[i] == null) {
						alert = new Modal(gf, "저장된 파일이 없습니다.", "경고");
						alert.setVisible(true);
					} else {
						gf.game(userName[i], items[i]);
					}
				}
			}
		}
	}
}
