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
	public Audio gameover = new Audio("audio/gameover.wav", false,-10);
	
	public EndPanel(GameFrame gf) {
		GamePanel.mainbg.stop();
		gameover.start();
		this.gf = gf;
		this.setLayout(null);

		end = gf.mkLabel("게임 오버", 60);
		end.setBounds(360, 260, 280, 70);
		this.add(end);

		score = gf.mkLabel("내 점수 : " + Integer.toString(gf.stage - 1) + "층", 30);
		int len = score.getText().length();
		score.setBounds(360, 345, 280, 50);
		this.add(score);

		home = gf.createBtn("홈으로 돌아가기");
		home.setBounds(750, 600, 200, 50);
		HomeBtnAction btn = new HomeBtnAction();
		home.addActionListener(btn);
		this.add(home);

		this.add(gf.backgrounds[10]);

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
}
