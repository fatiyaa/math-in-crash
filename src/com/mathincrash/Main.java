package com.mathincrash;

import javax.swing.JFrame;

import com.mathincrash.ui.GamePanel;

public class Main {

    public static void main(String[] args) throws Exception {
        JFrame window  = new JFrame("Math in Crash");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gp = new GamePanel();
        window.add(gp);
        gp.startThread();

        window.pack();
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setVisible(true);
    }
}