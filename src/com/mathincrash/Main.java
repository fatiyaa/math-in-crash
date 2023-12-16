package com.mathincrash;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.mathincrash.ui.GamePanel;

public class Main {

    public static void main(String[] args) throws Exception {
        JFrame window  = new JFrame("Math in Crash");
        ImageIcon icon = new ImageIcon("assets/Icon.png");

        GamePanel gp = new GamePanel();
        window.add(gp);
        gp.startThread();
        
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}