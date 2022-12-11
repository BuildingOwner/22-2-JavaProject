package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.SaveDataPanel.HomeBtnAction;

public class EndPanel extends JPanel {
	private JLabel end;
	private JLabel score;
	private JButton home;
	GameFrame gf;

	public EndPanel(GameFrame gf) {
		this.gf = gf;
		this.setLayout(null);

		end = new JLabel("게임 오버");
		end.setBounds(360, 90, 400, 50);
		Font font = new Font("", Font.BOLD, 60);
		end.setFont(font);
		this.add(end);

		score = new JLabel("내 점수 : " + Integer.toString(gf.stage - 1) + "층");
		score.setBounds(410, 180, 400, 50);
		font = new Font("", Font.PLAIN, 30);
		score.setFont(font);
		this.add(score);

		home = new JButton("홈으로 돌아가기");
		home.setBounds(750, 600, 200, 50);
		HomeBtnAction btn = new HomeBtnAction();
		home.addActionListener(btn);
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
}
