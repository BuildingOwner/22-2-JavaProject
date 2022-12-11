package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputNamePanel extends JPanel {
	private JLabel title;
	JButton submitBtn;
	JTextField nameField;
	GameFrame gf;
	String name;

	InputNamePanel(GameFrame gameF) {
		this.setLayout(null);
		this.gf = gameF;

		this.title = new JLabel("이름을 입력하세요");
		title.setFont(new Font("", Font.PLAIN, 40));
		title.setBounds(330, 200, 600, 70);
		this.nameField = new JTextField(12); // 이름 글자 수 제한 구현 하기
		nameField.setBounds(380, 300, 150, 30);
		this.submitBtn = new JButton("저장");
		submitBtn.setBounds(550, 300, 60, 30);

		submitBtn.addActionListener(new SubmitBtnAction());

		this.add(title);
		this.add(nameField);
		this.add(submitBtn);

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
					alert = new Modal(gf, "이름을 입력해 주셔야 게임이 시작됩니다.");
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
