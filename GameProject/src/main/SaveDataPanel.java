package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SaveDataPanel extends JPanel {

	private JLabel title;
	public JButton[] saves = new JButton[3];
	public JButton home;
	public Item[][] items = new Item[3][4];
	public GameFrame gf;
	public String[] userName = new String[3];

	public SaveDataPanel(GameFrame gameF) {
		this.setLayout(null);

		this.gf = gameF;

		title = new JLabel("저장하기");
		title.setBounds(390, 50, 230, 100);
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);

		for (int i = 0; i < 3; i++) {
			saves[i] = new JButton();
			saves[i].setBounds(300, 200 + i * 120, 400, 100);
			saves[i].setName("" + (i));
		}

		for (int i = 0; i < 3; i++) {
			JPanel tp = new JPanel();
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader("./SaveFiles/SaveFile" + i + ".txt")));
				if (!sc.hasNext()) {
					throw new IOException();
				}
				userName[i] = sc.next();
				tp.add(new JLabel(userName[i]));
				for (int j = 0; j < 4; j++) {
					try {
						items[i][j] = gf.itemP.returnItem(sc.next(), sc.nextInt());
						tp.add(new JLabel(items[i][j].name));
						tp.add(new JLabel(Integer.toString(items[i][j].power)));
					} catch (NoSuchElementException e) {
						items[i][j] = new Item();
						tp.add(new JLabel(" 아이템 없음 "));
					}

				}
				sc.close();

			} catch (IOException e) {
				for (int j = 0; j < 4; j++) {
					items[i][j] = new Item();
				}
				tp.add(new JLabel("저장된 파일이 없습니다."));
			}
			saves[i].add(tp);
		}

		home = new JButton("홈으로 돌아가기");
		home.setBounds(750, 600, 200, 50);
		HomeBtnAction btn = new HomeBtnAction();
		home.addActionListener(btn);

		this.add(title);
		SaveDataBtnAction saveBtn = new SaveDataBtnAction();
		for (int i = 0; i < 3; i++) {
			saves[i].addActionListener(saveBtn);
			this.add(saves[i]);
		}
		this.add(home);
	}

	class HomeBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn.getText().equals("홈으로 돌아가기")) {
				gf.redraw(gf.startP);
			}
		}
	}

	class SaveDataBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			for (int i = 0; i < 3; i++) {
				if (btn.getName().equals(Integer.toString(i))) {
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("./SaveFiles/SaveFile" + i + ".txt"));
						if (userName[i] == null) {
							bw.write(gf.player.name);
							for (int j = 0; j < gf.player.itemCnt; j++) {
								bw.newLine();
								bw.write(gf.player.items[j].name + " " + gf.player.items[j].power);
							}
							Modal m = new Modal(gf, "저장이 완료되었습니다.", "저장");
							m.setVisible(true);
							gf.redraw(gf.startP);
						}
						else {
							int confirm = JOptionPane.showConfirmDialog(null, "덮어 씌우겠습니까?", "경고", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
							if(confirm == 0) {
								bw.write(gf.player.name);
								for (int j = 0; j < gf.player.itemCnt; j++) {
									bw.newLine();
									bw.write(gf.player.items[j].name + " " + gf.player.items[j].power);
								}
								Modal m = new Modal(gf, "저장이 완료되었습니다.", "저장");
								m.setVisible(true);
								gf.redraw(gf.startP);
							}
						}
						bw.close();
					} catch (IOException e1) {

					}
				}
			}
		}
	}
}
