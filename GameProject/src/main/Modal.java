package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Modal extends JDialog {
    private JLabel alert = new JLabel();
    private JButton okBtn = new JButton("확인");

    public Modal(JFrame gameF, String msg) {
        super(gameF, "경고", true); // true는 모달 타입을 만들도록 지시
        setLayout(null);
        alert.setText(msg);
        alert.setHorizontalAlignment(JLabel.CENTER);
        alert.setBounds(10, 5, 230, 30);
        okBtn.setBounds(90, 70, 60, 30);
        add(alert);
        add(okBtn);
        this.setBounds(600, 300, 250, 150);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}