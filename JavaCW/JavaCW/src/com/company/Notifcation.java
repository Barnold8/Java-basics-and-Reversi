package com.company;

import javax.swing.*;

public class Notifcation extends JFrame {

    public Notifcation(String title,String window_message){
        JLabel message = new JLabel(window_message,SwingConstants.CENTER);

        this.add(message);
        this.setResizable(false);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(1920/2,1080/2,320,240);
    }



}
