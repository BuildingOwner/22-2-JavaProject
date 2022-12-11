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

    public Modal(JFrame gameF, String msg, String title) {
        super(gameF, title, true); // true는 모달 타입을 만들도록 지시
        setLayout(null);
        alert.setText(msg);
        alert.setHorizontalAlignment(JLabel.CENTER);
        alert.setBounds(10, 20, 230, 30);
        okBtn.setBounds(100, 70, 60, 30);
        add(alert);
        add(okBtn);
        this.setBounds(670, 370, 265, 150);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}