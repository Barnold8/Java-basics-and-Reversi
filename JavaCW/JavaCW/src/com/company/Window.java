package com.company;
// THIS CLASS HANDLES THE WINDOW(S) SO THE USER CAN INTERACT WITH THE GAME
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window {

    private final JFrame win;
    private Layout layout;
    private final int ID;

    Window(int ID){           // default constructor
        int w,h,x,y;    // Window bounds
        w = 300;
        h = w;
        x = (1920/2) - (w/2);
        y = (1080/2) - (h/2);
        win = new JFrame("Default title");
        win.setBounds(x,y,w,h);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout();
        this.ID = ID;
    }
    Window(int x, int y, int w, int h,int ID){         //Custom constructor with no title selected
        win = new JFrame("Default title");
        win.setBounds(x,y,w,h);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout();
        this.ID = ID;
    }
    Window(int x, int y, int w, int h, String title,int ID){//Custom constructor with title selected
        win = new JFrame(title);
        win.setBounds(x,y,w,h);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout();
        this.ID = ID;
    }

    public void show(){
        win.setVisible(true);
    }

    public void setLayout(){
        layout = new Layout(this.ID,this);
        layout.AddButtons(new Color(0, 176, 0), new Color(30,30,30));
        win.getContentPane().add(layout);

    }

    public Layout getLayout(){ // return panel
        return layout;
    }

    public void DEBUG_FUNC(){
        // VARIABLE SECTION
            String banner = "===========";
            String titleBan = banner+" Title "+banner;
            String geomBan = banner+" Geometry "+banner;
            String dataBan = banner+" Data "+banner;
            String IDBan = "__ID set__";
            String stateBan = "__State set__";
            String miscBan = banner+" Misc "+banner;
        // VARIABLE SECTION

        // FUNCTIONAL CODE
            System.out.println(titleBan);
            System.out.println(this.win.getTitle());
            System.out.println(geomBan);
            System.out.printf("X:%d\nY:%d\nW:%d\nH:%d%n",this.win.getX(),this.win.getY(),this.win.getWidth(),this.win.getHeight());
            // DATA SECTION
                System.out.println(dataBan);
                System.out.println(IDBan);
                printIDSet();
                System.out.println(stateBan);
                printStateSet();
            // DATA SECTION
            System.out.println(miscBan);
            System.out.printf("Window ID: %d\n",this.ID);

            System.out.println(banner + " END OF DEBUG " + banner);
        // FUNCTIONAL CODE
    }

    public void setOtherWin(Window w){ // to avoid ambiguities, allow setting of otherwin after constructor init
        layout.setOtherWin(w);
    }

    public void startGame(boolean windowType){
        /*layout.getButtons().get(startTile+i).setClickableIcon(black);*/
        if(windowType){
            getLayout().activateDisc(4,4,1);
            getLayout().activateDisc(3,4,2);
            getLayout().activateDisc(4,3,2);
            getLayout().activateDisc(3,3,1);
        }else{
            getLayout().activateDisc(4,4,2);
            getLayout().activateDisc(3,4,1);
            getLayout().activateDisc(4,3,1);
            getLayout().activateDisc(3,3,2);
        }

    }

    private void printIDSet(){
        ArrayList<Clickable> buttons = layout.getButtons();
        for(int i = 0; i < buttons.size(); i ++){
            if(i % 8 == 0){
              System.out.printf("\n");
            }
            System.out.printf("[ID: %d]",buttons.get(i).getID());
            if(i < 9){System.out.printf(" ");}
        }
        System.out.printf("\n\n");
    }

    public void printStateSet(){
        ArrayList<Clickable> buttons = this.layout.getButtons();
        for(int i = 0; i < buttons.size(); i ++){
            if(i % 8 == 0){
                System.out.printf("\n");
            }
            System.out.printf("[State: %d] ",buttons.get(i).getPlayerState());

        }
        System.out.printf("\n\n");
    }

    public void set_Title(String title){
        this.win.setTitle(title);
    }

    public int getID(){return this.ID;};

    // CUSTOM GAMES FOR TESTING PURPOSES
    public void startCustom(){
        System.out.println("startCustom");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this.layout.getButton(0,0).setClickableIcon(black);


        this.layout.getButton(1,1).setClickableIcon(white);


        this.layout.getButton(2,2).setClickableIcon(white);


        this.layout.getButton(3,3).setClickableIcon(white);

        this.layout.getButton(3,4).setClickableIcon(black);


        this.layout.getButton(4,4).setClickableIcon(white);


        this.layout.getButton(5,5).setClickableIcon(black);




    }
    public void startCustom1(){
        System.out.println("startCustom1");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this.layout.getButton(0,1).setClickableIcon(black);


        this.layout.getButton(1,2).setClickableIcon(white);


        this.layout.getButton(2,3).setClickableIcon(white);


        this.layout.getButton(3,4).setClickableIcon(white);


        this.layout.getButton(4,5).setClickableIcon(white);

        this. layout.getButton(5,5).setClickableIcon(white);


        this.layout.getButton(5,4).setClickableIcon(black);


    }
    public void startCustom2(){
        System.out.println("startCustom2");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this.layout.getButton(0,1).setClickableIcon(black);


        this.layout.getButton(1,2).setClickableIcon(white);


        this.layout.getButton(2,3).setClickableIcon(black);


        this.layout.getButton(3,4).setClickableIcon(white);


        this.layout.getButton(4,5).setClickableIcon(white);


        this.layout.getButton(5,5).setClickableIcon(white);


        this.layout.getButton(5,4).setClickableIcon(black);


    }
    public void startCustom3(){
        System.out.println("startCustom3");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this.layout.getButton(0,1).setClickableIcon(black);


        this.layout.getButton(1,2).setClickableIcon(white);


        this.layout.getButton(2,3).setClickableIcon(black);


        this.layout.getButton(3,4).setClickableIcon(white);


        this.layout.getButton(4,5).setClickableIcon(black);


        this.layout.getButton(5,5).setClickableIcon(white);


        this.layout.getButton(5,4).setClickableIcon(black);


    }
    public void startCustom4(){
        System.out.println("startCustom4");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this.layout.getButton(0,1).setClickableIcon(white);


        this.layout.getButton(1,2).setClickableIcon(black);


        this.layout.getButton(2,3).setClickableIcon(white);


        this.layout.getButton(3,4).setClickableIcon(black);

        this.layout.getButton(4,5).setClickableIcon(white);


        this.layout.getButton(5,5).setClickableIcon(black);

        this. layout.getButton(5,4).setClickableIcon(white);

    }
    public void startCustom5(){
        System.out.println("startCustom5");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this. layout.getButton(0,1).setClickableIcon(white);

        this.layout.getButton(1,1).setClickableIcon(black);

        this.layout.getButton(1,2).setClickableIcon(black);

        this. layout.getButton(2,3).setClickableIcon(white);

        this.layout.getButton(3,4).setClickableIcon(black);

        this.layout.getButton(4,4).setClickableIcon(black);

        this.layout.getButton(4,5).setClickableIcon(white);

        this. layout.getButton(5,5).setClickableIcon(black);

        this.layout.getButton(5,4).setClickableIcon(white);

    }
    public void startCustom6(){
        System.out.println("startCustom6");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
        this.layout.getButton(0,1).setClickableIcon(white);

        this.layout.getButton(1,2).setClickableIcon(black);

        this.layout.getButton(2,2).setClickableIcon(white);

        this.layout.getButton(2,3).setClickableIcon(white);

        this.layout.getButton(3,4).setClickableIcon(black);

        this.layout.getButton(4,4).setClickableIcon(white);

        this.layout.getButton(4,5).setClickableIcon(white);

        this.layout.getButton(5,5).setClickableIcon(black);

        this.layout.getButton(5,4).setClickableIcon(white);

    }
    public void startCustom7(){
        System.out.println("startCustom6");
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);

        this.layout.getButton(4,3).setClickableIcon(white);
        this.layout.getButton(5,3).setClickableIcon(black);

    }
    // CUSTOM GAMES FOR TESTING PURPOSES
}
