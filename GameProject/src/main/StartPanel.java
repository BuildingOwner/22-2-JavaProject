package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel {
	private JLabel title;
	public JButton[] button = new JButton[3];
	public GameFrame gameF;

	public StartPanel(GameFrame gameF) {
		this.setLayout(null);

		this.gameF = gameF;

		title = new JLabel("격투 게임");
		title.setBounds(390, 100, 230, 100);
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);

		button[0] = new JButton("새 게임");
		button[0].setBounds(425, 330, 150, 50);
		button[1] = new JButton("이어 하기");
		button[1].setBounds(425, 430, 150, 50);
		button[2] = new JButton("도움말");
		button[2].setBounds(425, 530, 150, 50);

		SPBtnAction btn = new SPBtnAction();
		this.add(title);
		for (int i = 0; i < 3; i++) {
			button[i].addActionListener(btn);
			this.add(button[i]);
		}

	}

	class SPBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn.getText().equals("도움말")) {
				gameF.getContentPane().removeAll();
				gameF.add(gameF.helpP);
			} else if (btn.getText().equals("이어 하기")) {
				gameF.getContentPane().removeAll();
				gameF.add(gameF.saveP);
			} else {
				gameF.getContentPane().removeAll();
				gameF.add(gameF.gameP);
				Item[] items = new Item[4];
				for (int i = 0; i < 4; i++) {
					items[i] = new Item();
				}
				gameF.run(null, items);
			}
			gameF.revalidate();
			gameF.repaint();

		}

	}

}
