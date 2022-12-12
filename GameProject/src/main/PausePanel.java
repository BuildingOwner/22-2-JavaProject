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
		
		title = new JLabel("일시정지");
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);
		title.setBounds(395, 130, 210, 60);
		title.setOpaque(true);
		title.setBackground(Color.white);
		this.add(title);
		
		BtnAction btn = new BtnAction();
		
		resume = new JButton("돌아가기");
		resume.setBounds(400, 280, 200, 50);
		resume.addActionListener(btn);
		this.add(resume);
		
		saveQuit = new JButton("저장하고 나가기");
		saveQuit.setBounds(400, 390, 200, 50);
		saveQuit.addActionListener(btn);
		this.add(saveQuit);
		
		home = new JButton("홈으로 돌아가기");
		home.setBounds(400, 500, 200, 50);
		home.addActionListener(btn);
		this.add(home);
		
		this.add(gf.backgrounds[6]);
	}
	
	class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			if(btn.getText().equals("돌아가기")) {
				gf.resumeStage();
			}

			if (btn.getText().equals("홈으로 돌아가기")) {
				gf.stage = 1;
				gf.redraw(gf.startP);
			}
			
			if(btn.getText().equals("저장하고 나가기")) {
				gf.redraw(new SaveDataPanel(gf));
			}
		}
	}
}
