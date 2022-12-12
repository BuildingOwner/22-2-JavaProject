package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class InputNamePanel extends JPanel {
	private JLabel title;
	JButton submitBtn;
	JTextField nameField;
	GameFrame gf;
	String name;

	InputNamePanel(GameFrame gameF) {
		this.setLayout(null);
		this.gf = gameF;

		this.title = gf.mkLabel("이름을 입력하세요", 40);
		title.setBounds(320, 230, 350, 70);

		this.nameField = new JTextField(12);
		nameField.setBounds(380, 340, 150, 30);
		nameField.setForeground(Color.white);
		nameField.setOpaque(true);
		nameField.setBackground(Color.black);
		nameField.setBorder(new LineBorder(Color.white, 3));

		this.submitBtn = gf.createBtn("저장");
		submitBtn.setBounds(550, 340, 60, 30);
		submitBtn.addActionListener(new SubmitBtnAction());

		this.add(title);
		this.add(nameField);
		this.add(submitBtn);
		this.add(gf.backgrounds[9]);

	}

	public String getName() {
		if (name != null)
			return name;
		else
			return "이름없음";
	}

	class SubmitBtnAction implements ActionListener {
		private Modal alert;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitBtn) {
				if (nameField.getText().equals("")) {
					alert = new Modal(gf, "이름을 입력해 주셔야 게임이 시작됩니다.", "경고");
					alert.setVisible(true);
				}

				else {
					name = nameField.getText();
					String userName = gf.nameP.getName();
					Item[] items = new Item[4];
					for (int i = 0; i < 4; i++) {
						items[i] = new Item();
					}
					gf.game(userName, items);
				}
			}
		}
	}
}
