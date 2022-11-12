package main;

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

public class SavePanel extends JPanel {
	private JLabel title;
	public JButton[] saves = new JButton[3];
	public JButton home;
	public Item[] items = new Item[4];
	public GameFrame gameF;

	public SavePanel(GameFrame gameF) {
		this.setLayout(null);

		this.gameF = gameF;

		title = new JLabel("불러 오기");
		title.setBounds(390, 50, 230, 100);
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);

		for (int i = 0; i < 3; i++) {
			saves[i] = new JButton();
			saves[i].setBounds(300, 200 + i * 120, 400, 100);
		}

		for (int i = 0; i < 3; i++) {
			JPanel tp = new JPanel();
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader("./SaveFiles/SaveFile" + i + ".txt")));
				if(!sc.hasNext()) {
					throw new IOException();
				}
				for (int j = 0; j < 4; j++) {
					try {
						items[i] = new Item(sc.next(), sc.nextInt());
						tp.add(new JLabel(items[i].name));
						tp.add(new JLabel(Integer.toString(items[i].level)));
					} catch (NoSuchElementException e) {
						tp.add(new JLabel(" 아이템 없음 "));
					}

				}

			} catch (IOException e) {
				tp.add(new JLabel("저장된 파일이 없습니다."));
			}
			saves[i].add(tp);
		}

		home = new JButton("홈으로 돌아가기");
		home.setBounds(400, 600, 200, 50);
		SaPBtnAction btn = new SaPBtnAction();
		home.addActionListener(btn);

		this.add(title);
		for (int i = 0; i < 3; i++) {
			this.add(saves[i]);
		}
		this.add(home);

	}

	class SaPBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("홈으로 돌아가기")) {
				gameF.getContentPane().removeAll();
				gameF.add(gameF.startP);
				gameF.revalidate();
				gameF.repaint();
			}

		}

	}

}
