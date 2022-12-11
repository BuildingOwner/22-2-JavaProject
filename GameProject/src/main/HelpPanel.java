package main;

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

		title = new JLabel("조작 방법");
		title.setBounds(390, 100, 230, 100);
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);

		discript = new JLabel("<html>A키를 눌러 공격<br>D키를 눌러 방어<br>화살표를 눌러 피하세요.</html>");
		discript.setBounds(300, 150, 300, 300);

		home = new JButton("홈으로 돌아가기");
		home.setBounds(750, 600, 200, 50);
		HPBtnAction btn = new HPBtnAction();
		home.addActionListener(btn);

		this.add(title);
		this.add(discript);
		this.add(home);

	}

	class HPBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("홈으로 돌아가기")) {
				gf.getContentPane().removeAll();
				gf.add(gf.startP);
				gf.revalidate();
				gf.repaint();
			}

		}

	}

}
