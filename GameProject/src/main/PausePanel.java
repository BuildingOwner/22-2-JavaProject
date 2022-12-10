package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PausePanel extends JPanel {
	private JButton resume;
	private JButton saveQuit;
	private JButton home;
	private GameFrame gf;
	
	public PausePanel(GameFrame gf) {
		this.setLayout(null);
		this.gf = gf;
		
		BtnAction btn = new BtnAction();
		
		resume = new JButton("돌아가기");
		resume.setBounds(400, 250, 200, 50);
		resume.addActionListener(btn);
		this.add(resume);
		
		saveQuit = new JButton("저장하고 나가기");
		saveQuit.setBounds(400, 360, 200, 50);
		this.add(saveQuit);
		
		home = new JButton("홈으로 돌아가기");
		home.setBounds(400, 470, 200, 50);
		home.addActionListener(btn);
		this.add(home);
	}
	
	class BtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			
			if(btn.getText().equals("돌아가기")) {
				gf.resumeStage();
			}

			if (btn.getText().equals("홈으로 돌아가기")) {
				gf.redraw(gf.startP);
			}
		}
	}
}
