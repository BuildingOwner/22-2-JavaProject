package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PausePanel extends JPanel {
	private JLabel title;
	private JButton resume;
	private JButton saveQuit;
	private JButton home;
	private GameFrame gf;

	public PausePanel(GameFrame gf) {
		this.setLayout(null);
		this.gf = gf;

		title = gf.mkLabel("일시 정지", 50);
		title.setBounds(380, 130, 240, 60);
		this.add(title);

		BtnAction btn = new BtnAction();

		resume = gf.createBtn("돌아가기");
		resume.setBounds(420, 280, 160, 50);
		resume.addActionListener(btn);
		this.add(resume);

		saveQuit = gf.createBtn("저장하고 나가기");
		saveQuit.setBounds(420, 390, 160, 50);
		saveQuit.addActionListener(btn);
		this.add(saveQuit);

		home = gf.createBtn("홈으로 돌아가기");
		home.setBounds(420, 500, 160, 50);
		home.addActionListener(btn);
		this.add(home);

		this.add(gf.backgrounds[6]);
	}

	class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn.getText().equals("돌아가기")) {
				gf.resumeStage();
			}

			if (btn.getText().equals("홈으로 돌아가기")) {
				gf.stage = 1;
				gf.redraw(gf.startP);
			}

			if (btn.getText().equals("저장하고 나가기")) {
				gf.redraw(new SaveDataPanel(gf));
			}
		}
	}
}
