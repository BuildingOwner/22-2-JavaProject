package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ItemPanel extends JPanel {
	private JLabel title;
	public JButton[] choose = new JButton[3];
	public GameFrame gf;
	public Item[] ksy = new Item[1]; // 나중에 아이템 수 만큼 늘릴 예정

	public ItemPanel(GameFrame gf) {
		this.setLayout(null);
		this.gf = gf;
		initItem();

		title = new JLabel("전리품 획득!");
		title.setBounds(200, 50, 400, 50);
		Font font = new Font("", Font.BOLD, 50);
		title.setFont(font);
		this.add(title);

		int[] n = new int[3];
		for (int i = 0; i < n.length; i++) {
			n[i] = (int) (Math.random() * 1); // 아이템 수 만큼 늘려야 함
//			for(int j=0; j<n.length; j++) {
//				if(n[i] == n[j]) {
//					i--;
//				}
//			}  아이템의 갯수가 3개 이상일때 가동
		}

		for (int i = 0; i < choose.length; i++) {
			JPanel tp = new JPanel();
			tp.add(new JLabel(ksy[n[i]].image.getIcon()));
			tp.add(new JLabel(ksy[n[i]].name + "  공격력 :" + Integer.toString(ksy[n[i]].power) + "  재사용 대기시간 :"
					+ Integer.toString(ksy[n[i]].remain)));
			choose[i] = new JButton();
			choose[i].add(tp);
			choose[i].setName(Integer.toString(n[i]));
			choose[i].addActionListener(new ItemBtnAction());
			choose[i].setBounds(140, 150 + i * 140, 400, 100);
			this.add(choose[i]);
		}

	}

	public void initItem() {
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader("./SaveFiles/items.txt")));
			if (!sc.hasNext()) {
				throw new IOException();
			}
			for (int i = 0; i < ksy.length; i++) {
				ksy[i] = new Item();
				try {
					ksy[i].name = sc.next();
					ksy[i].power = sc.nextInt();
					ksy[i].remain = sc.nextInt();
					ksy[i].setImage();
				} catch (NoSuchElementException e) {

				}
			}
			sc.close();
		} catch (IOException e) {

		}

	}

	public Item returnItem(String name, int power) {
		for (int i = 0; i < ksy.length; i++) {
			if (name.equals(ksy[i].name)) {
				return new Item(ksy[i], power);
			}
		}
		return null;
	}

	class ItemBtnAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (gf.player.itemCnt >= 4) {
				String input = JOptionPane.showInputDialog("아이템이 가득 찼습니다.\n아이템을 교체하시겠습니까?\n교체할 아이템 슬롯의\n번호를 입력해 주세요.",
						"(1~4)");
				if(input != null) {
					gf.player.items[Integer.parseInt(input)-1] = ksy[Integer.parseInt(btn.getName())];
				}
				gf.itemF.setVisible(false);
				gf.nextStage();
				return;
			}
			gf.player.items[gf.player.itemCnt++] = ksy[Integer.parseInt(btn.getName())];
			gf.itemF.setVisible(false);
			gf.nextStage();
			gf.flag = true;
		}

	}

}
