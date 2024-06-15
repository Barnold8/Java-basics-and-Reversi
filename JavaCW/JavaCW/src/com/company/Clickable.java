package com.company;
// THIS CLASS REPRESENTS THE CLICKABLE ELEMENTS THAT A PLAYER CAN CLICK
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class Clickable extends JButton {

    private static int ID = 0; // Allows incremental button ID generation
    private final int currID;  // Allows ID assignment to each button
    private Layout copyOfLayout; // Keep a reference to the parent layout
    private final int[] coords = {-55,-55}; // big numbers to notice some bugs
    private Color col =  null;

    Clickable(){
        ID++;
        super.setPreferredSize(new Dimension(10,10));
        super.setFocusPainted(false);
        currID = ID;

    }
    Clickable(boolean isDisc,Layout copy){
        ID++;
        super.setPreferredSize(new Dimension(10,10));
        super.setFocusPainted(false);

        copyOfLayout = copy;
        currID = ID;

        super.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDisc) {
                    // Disc logic
                    copyOfLayout.processInput(coords,currID);
                }
                else{
                    copyOfLayout.greedyAI(currID);
                }
            }
        });

    }
    Clickable(String title,boolean isDisc,Layout copy){
        ID++;
        super.setText(title);
        super.setPreferredSize(new Dimension(10,10));
        super.setFocusPainted(false);

        copyOfLayout = copy;
        currID = ID;


        super.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isDisc) {
                    // Disc logic
                    copyOfLayout.processInput(coords,currID);
                }
                else{
                    copyOfLayout.greedyAI(currID);
                }
            }
        });

    }

    public int getID(){return currID;}
    public int[] getCoords(){return coords;}
    public void setCoords(int x, int y){
        coords[0] = x;
        coords[1] = y;
    }

    public void setStyle(Color bg){
        this.setBackground(bg);

    }
    public void setStyle(Color bg, Color bd){
        this.setBackground(bg);
        this.setBorder(new LineBorder(bd));
    }

    public Icon makeIcon(Color col){ // Col is used to set the black or white disc
        int w = getPreferredSize().width*3; // Keeping to width to minimise variable count (h would be equal to w)
        BufferedImage img = new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB);
        Graphics2D disc = img.createGraphics();
        disc.setColor(col);
        disc.setStroke(new BasicStroke());
        disc.fillOval(0, 0, w, w);

        disc.dispose();
        return new ImageIcon(img);
    }

    public void setClickableIcon(Color col){
        this.col = col;
        setIcon(makeIcon(col));
        setDisabledIcon(getIcon());
        setEnabled(false);
    }

    /*public int getPlayerState(){return this.PlayerState;}*/
    public int getPlayerState(){
        if(this.col == null){return -1;}
        int RGB = this.col.getRed() + this.col.getBlue() + this.col.getGreen();
        return switch (RGB) {
            case 765 -> 1;
            case 0 -> 0;
            case 255 -> -1;
            default -> -1;
        };

    }

    public Color getCol(){
        return this.col;
    }



}

// code below shows p1 and p2 dots on screen respectively
/*                   copyOfLayout.activateDisc(currID -1);
                    copyOfLayout.activateOthersDisc(64 - currID );*/
