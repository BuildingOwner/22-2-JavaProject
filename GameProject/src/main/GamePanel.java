package main;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public GamePanel(Player p, Monster m) {

		draw(p, 500, 700);
	}

	private void draw(GameObject o, int x, int y) {
		o.obj.setVisible(false);
		move(o, x, y);
		o.obj.setVisible(true);
	}

	private void move(GameObject o, int x, int y) {
		o.obj.setLocation(x, y);
	}
}
