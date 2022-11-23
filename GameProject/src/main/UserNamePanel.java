package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserNamePanel extends JPanel {
    private  JLabel title;
    JButton submitBtn;
    JTextField nameField;
    GameFrame gameF;
    String name;

    UserNamePanel(GameFrame gameF) {
        this.setLayout(null);
        this.gameF = gameF;

        this.title = new JLabel("이름을 입력하세요");
        title.setFont(new Font("",Font.PLAIN,40));
        title.setBounds(330,200,600,70);
        this.nameField = new JTextField(12); // 이름 글자 수 제한 구현 하기
        nameField.setBounds(380,300,150,30);
        this.submitBtn = new JButton("저장");
        submitBtn.setBounds(550,300,60,30);

        submitBtn.addActionListener(new SubmitBtnAction());

        this.add(title);
        this.add(nameField);
        this.add(submitBtn);

    }

    public String getName() {
        if(name != null)
            return name;
        else
            return "이름없음";
    }

    class SubmitBtnAction implements ActionListener {
        private  Modal alert;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == submitBtn){
                if(nameField.getText().equals("")){
                    alert = new Modal(gameF, "이름을 입력해 주셔야 게임이 시작됩니다.");
                    alert.setVisible(true);
                }

                else {
                    name = nameField.getText();
                }
            }
            if(name != null) {
                gameF.getContentPane().removeAll();
                gameF.add(gameF.gameP);
                gameF.revalidate();
                gameF.repaint();
            }
        }
    }
}

