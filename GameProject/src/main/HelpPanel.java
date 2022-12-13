package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	private JLabel title;
	private JLabel discript;
	public JButton home;
	public GameFrame gf;

	public HelpPanel(GameFrame gameF) {
		this.setLayout(null);

		this.gf = gameF;

		title = gf.mkLabel("도움말", 50);
		title.setBounds(390, 100, 230, 100);

		discript = gf.mkLabel(
				"<html>" + "A키를 눌러 공격하세요.<br>" + "Q, W, E, R키를 눌러 스킬을 사용하세요.<br>" + "화살표를 좌우를 눌러 피하세요." + "</html>",
				20);
		discript.setBounds(300, 250, 400, 200);

		home = gf.createBtn("홈으로 돌아가기");
		home.setBounds(750, 600, 200, 50);
		HPBtnAction btn = new HPBtnAction();
		home.addActionListener(btn);

		this.add(title);
		this.add(discript);
		this.add(home);
		this.add(gf.backgrounds[7]);

	}

	class HPBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("홈으로 돌아가기")) {
				StartPanel.stbg.start();
				gf.getContentPane().removeAll();
				gf.add(gf.startP);
				gf.revalidate();
				gf.repaint();
			}

		}

	}

}
