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
import javax.swing.JPanel;

public class SavePanel extends JPanel {
    private JLabel title;
    public JButton[] saves = new JButton[3];
    public JButton home;
    public Item[][] items = new Item[3][4];
    public GameFrame gf;
    public String[] userName = new String[3];

    public SavePanel(GameFrame gameF) {
        this.setLayout(null);

        this.gf = gameF;

        title = new JLabel("불러 오기");
        title.setBounds(390, 50, 230, 100);
        Font font = new Font("", Font.BOLD, 50);
        title.setFont(font);

        for (int i = 0; i < 3; i++) {
            saves[i] = new JButton();
            saves[i].setBounds(300, 200 + i * 120, 400, 100);
            saves[i].setName("저장소" + (i + 1));
        }

        for (int i = 0; i < 3; i++) {
            JPanel tp = new JPanel();
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader("./SaveFiles/SaveFile" + i + ".txt")));
                if (!sc.hasNext()) {
                    throw new IOException();
                }
                userName[i] = sc.next();
                tp.add(new JLabel(userName[i]));
                for (int j = 0; j < 4; j++) {
                    try {
                        items[i][j] = new Item(sc.next(), sc.nextInt());
                        tp.add(new JLabel(items[i][j].name));
                        tp.add(new JLabel(Integer.toString(items[i][j].level)));
                    } catch (NoSuchElementException e) {
                        items[i][j] = new Item();
                        tp.add(new JLabel(" 아이템 없음 "));
                    }

                }
                sc.close();

            } catch (IOException e) {
                for (int j = 0; j < 4; j++) {
                    items[i][j] = new Item();
                }
                tp.add(new JLabel("저장된 파일이 없습니다."));
            }
            saves[i].add(tp);
        }

        home = new JButton("홈으로 돌아가기");
        home.setBounds(750, 600, 200, 50);
        HomeBtnAction btn = new HomeBtnAction();
        home.addActionListener(btn);

        this.add(title);
        SaveBtnAction saveBtn = new SaveBtnAction();
        for (int i = 0; i < 3; i++) {
            saves[i].addActionListener(saveBtn);
            this.add(saves[i]);
        }
        this.add(home);

    }

    class HomeBtnAction implements ActionListener {

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

    class SaveBtnAction implements ActionListener {
        private Modal alert;

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            for (int i = 0; i < 3; i++) {
                if (btn.getName().charAt(3) == (char) (i + 1 + '0')) {
                    if (userName[i] == null) {
                        alert = new Modal(gf, "저장된 파일이 없습니다.");
                        alert.setVisible(true);
                    } else {
                        gf.game(userName[i], items[i]);
                    }
                }
            }
        }
    }
}
