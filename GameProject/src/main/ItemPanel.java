package main;

import java.awt.Color;
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
	public Item[] ksy = new Item[5]; // 나중에 아이템 수 만큼 늘릴 예정
	public Audio itemget = new Audio("audio/itemget.wav", false,-13);
	public Audio shield = new Audio("audio/shield.wav", false,-3);
	public Audio heal = new Audio("audio/heal.wav", false,-8);
	public Audio select = new Audio("audio/select.wav", false,-10);
	
	public ItemPanel(GameFrame gf) {
		this.setLayout(null);
		this.gf = gf;
		initItem();
		title = gf.mkLabel("전리품 획득!", 50);
		title.setBounds(175, 30, 340, 70);
		this.add(title);
		int[] n = new int[3];
		for (int i = 0; i < n.length; i++) {
			n[i] = (int) (Math.random() * 5); // 아이템 수 만큼 늘려야 함
			for (int j = 0; j < i; j++) {
				if (n[i] == n[j]) {
					i--;
				}
			} // 아이템의 갯수가 3개 이상일때 가동
		}
		
		for (int i = 0; i < choose.length; i++) {
			JPanel tp = new JPanel();
			tp.setOpaque(true);
			tp.setBackground(Color.black);
			tp.add(new JLabel(ksy[n[i]].image.getIcon()));
			tp.add(gf.mkLabel(ksy[n[i]].name, 30));
			if (ksy[n[i]].type == 0) {
				tp.add(gf.mkLabel("  공격력 :" + Integer.toString(ksy[n[i]].power) + "  재사용 대기시간 :"
						+ Integer.toString(ksy[n[i]].remain), 0));
			} else if (ksy[n[i]].type == 1) {
				tp.add(gf.mkLabel("  방어력 :" + Integer.toString(ksy[n[i]].amount), 0));
			} else {
				tp.add(gf.mkLabel("  회복량 :" + Integer.toString(ksy[n[i]].amount), 0));
			}

			tp.setBounds(10, 10, 380, 110);
			choose[i] = gf.createBtn("");
			choose[i].setLayout(null);
			choose[i].add(tp);
			choose[i].setName(Integer.toString(n[i]));
			choose[i].addActionListener(new ItemBtnAction());
			choose[i].setBounds(140, 120 + i * 140, 400, 130);
			this.add(choose[i]);
		}
		itemget.start(); //게임 켤 때도 남
		this.add(gf.backgrounds[11]);
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
					int type = sc.nextInt();
					ksy[i].name = sc.next();
					if (type == 0) {
						ksy[i].power = sc.nextInt() * (1 + gf.stage / 10);
						ksy[i].remain = sc.nextInt();
					} else {
						ksy[i].amount = sc.nextInt();
					}
					ksy[i].type = type;
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
			if (ksy[Integer.parseInt(btn.getName())].type == 0) {
				Item ksy2 = new Item(ksy[Integer.parseInt(btn.getName())], ksy[Integer.parseInt(btn.getName())].power);
				if (gf.player.itemCnt >= 4) {
					String input = JOptionPane
							.showInputDialog("아이템이 가득 찼습니다.\n아이템을 교체하시겠습니까?\n교체할 아이템 슬롯의\n번호를 입력해 주세요.", "(1~4)");
					if (input != null) {
						gf.player.items[Integer.parseInt(input) - 1] = ksy2;
					}
					gf.itemF.setVisible(false);
					gf.nextStage();
					return;
				}
				select.start();
				gf.player.items[gf.player.itemCnt++] = ksy2;
			} else if (ksy[Integer.parseInt(btn.getName())].type == 1) {
				shield.start();
				gf.player.armor += ksy[Integer.parseInt(btn.getName())].amount;
			} else {
				heal.start();
				gf.player.hp += ksy[Integer.parseInt(btn.getName())].amount;
				if (gf.player.hp > 100) {
					gf.player.hp = 100;
				}
			}

			gf.itemF.dispose();
			gf.nextStage();
			gf.flag = true;
		}
	}
}
