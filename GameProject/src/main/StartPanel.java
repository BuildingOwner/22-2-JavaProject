package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private JLabel title;
	private JLabel background;
	public JButton[] button = new JButton[3];
	public GameFrame gf;
	public static Audio stbg = new Audio("audio/stbg.wav", true, -10);

	public StartPanel(GameFrame gameF) {
		this.setLayout(null);

		this.gf = gameF;

		title = gf.mkLabel("무한 동굴", 50);
		title.setBounds(385, 120, 230, 80);
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);
		this.add(title);

		background = new JLabel(gf.screenImage[1]);
		background.setSize(1000, 700);

		button[0] = gf.createBtn("새 게임");
		button[0].setBounds(425, 330, 150, 50);
		button[1] = gf.createBtn("이어 하기");
		button[1].setBounds(425, 430, 150, 50);
		button[2] = gf.createBtn("도움말");
		button[2].setBounds(425, 530, 150, 50);

		SPBtnAction btn = new SPBtnAction();
		for (int i = 0; i < 3; i++) {
			button[i].addActionListener(btn);
			this.add(button[i]);
		}
		stbg.start();
		this.add(gf.backgrounds[0]);

	}

	class SPBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("도움말")) {
				gf.redraw(gf.helpP);
				stbg.stop();
			} else if (btn.getText().equals("이어 하기")) {
				gf.saveP = new SavePanel(gf);
				gf.redraw(gf.saveP);
				stbg.stop();
			} else {
				gf.nameP = new InputNamePanel(gf);
				gf.redraw(gf.nameP);
				stbg.stop();

			}

		}

	}

}
