package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

public class GameFrame extends JFrame {
    public Scanner sc = new Scanner(System.in);
    public StartPanel startP;
    public HelpPanel helpP;
    public SavePanel saveP;
    public GamePanel gameP;
    public UserNamePanel nameP;

    public GameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300, 100, 1000, 700);

        startP = new StartPanel(this);
        helpP = new HelpPanel(this);
        saveP = new SavePanel(this);
        gameP = new GamePanel();

        this.add(startP);
        this.setVisible(true);
    }

    public void run(String userName, Item[] items) {
        if (userName == null) {
            // 이름 생성 화면 호출
            nameP = new UserNamePanel(this);
            this.getContentPane().removeAll();
            this.add(nameP);
            this.revalidate();
            this.repaint();
            userName = nameP.getName();
        }


        Player p = new Player(100, 1, 0, userName, items);
        Monster m = null;
        m = createMonster();

        // 스래드로 몬스터의 공격과 플레이어의 공격, 게임 프레임의 게임 진행을 스레드로 구현 해야 함
    }

    private Monster createMonster() {
        Monster m = null;
        int i = (int) Math.random() + 1;
        switch (i) { // 몬스터들 수 만큼 늘어나야 함
            case 1:
                m = new Monster(100, 1, 0, "test monster"); // 각자 몬스터 클래스 들어갈 예정
        }
        return m;
    }

    public static void main(String[] args) {
        GameFrame game = new GameFrame();

    }
}
