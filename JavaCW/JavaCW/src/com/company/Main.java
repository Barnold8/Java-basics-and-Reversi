package com.company;
// SIMPLE MAIN CLASS TO START EXECUTION OF PROGRAM
// https://www.youtube.com/watch?v=zFrlu3E18BA
public class Main {

    static boolean player = false;

    public static void main(String[] args) {
        Window win = new Window(100,100,500,400,"|Reversi P1| Your turn",1);
        Window win1 = new Window(100+500,100,500,400,"|Reversi P2| Player 1's go! (black)",2);

        win.setOtherWin(win1);
        win1.setOtherWin(win);

        win1.show();
        win.show();

        /*win.startCustom3();*/

/*        win.startGame(true);
        win1.startGame(false);*/

        win.startCustom7();
        win1.startCustom7();

        /*win.DEBUG_FUNC();*/
        /* win1.DEBUG_FUNC();*/
    }
}
