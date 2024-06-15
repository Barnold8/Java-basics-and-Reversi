package com.company;
// THIS CLASS HANDLES THE LAYOUT AND HOLDS INFORMATION REGARDING IT
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

// 0 = BLACK
// 1 = WHITE

// NOTE GAME IS BUGGY IN SOME DIRECTIONS (DIAGONAL)

public class Layout extends JPanel {

    private final GridBagConstraints config;
    private Clickable button;
    private final ArrayList<Clickable> buttons = new ArrayList<Clickable>();
    private final int ID;             // to associate a layout with a player
    private Window otherWin;    // reference the otherWindow so we can manipulate its layout
    private final Window currentWin;  // reference to the parent window

    Layout(int ID, Window win) {
        super(new GridBagLayout());
        this.config = new GridBagConstraints();
        this.ID = ID;
        this.currentWin = win;
    }


    private boolean toBool(int num) { // Since java doesn't like to match ints to booleans like c/c++ does, this is needed
        return (num >= 1);
    }

    public void AddButtons(Color bg, Color bd) { // Automatically adds the buttons to the panel
        this.config.fill = GridBagConstraints.BOTH;
        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                this.button = new Clickable(true, this);
                this.button.setStyle(bg, bd);
                this.buttons.add(button);
                /*button.paint();*/
                this.config.weightx = 1;
                this.config.weighty = 1;
                this.config.gridx = i;
                this.config.gridy = y;
                this. button.setCoords(i, y);
                /*config.ipady = height/(int)button.getPreferredSize().getHeight(); (Line of code im too annoyed to remove)*/
                this.add(button, config);               // It was made because i forgot weighty was a thing...
            }

        }
        this.button = new Clickable("Greedy AI", false, this);
        this.config.weightx = 1;
        this. config.weighty = 1;
        this.config.gridx = 0;
        this.config.gridy = 9;
        this.config.gridwidth = 10;
        this.add(button, config);

    }

    public void activateDisc(int x, int y, int col) {
        if ((col <= -1 || col >= 3)) {
            System.out.printf("ERROR: colour out of range\nThe colour is type: %d", col);
            return;
        }

        ArrayList<Color> colours = new ArrayList<>();
        colours.add(new Color(210, 10, 100));
        colours.add(new Color(0, 0, 0));
        colours.add(new Color(255, 255, 255));
        this.getButton(x,y).setClickableIcon(colours.get(col));

    }

    public void setOtherWin(Window win) {
        otherWin = win;
    }

    public ArrayList<Clickable> getButtons() {
        return buttons;
    }

    public Clickable getButton(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("X given: " + x + " Y given " + y);
            return null;
        }
        int[] coords = {x, y};
        for (Clickable clickable : buttons) {
            // Long line due to array comparison not working?
            if (coords[0] == clickable.getCoords()[0] && coords[1] == clickable.getCoords()[1]) {
                return clickable;
            }/*else{
                System.out.printf("%d %d is not equal to %d %d\n",x,y,clickable.getCoords()[0],clickable.getCoords()[1]);
            }*/
        }
        System.out.printf("BUTTON WITH COORDS %d %d NOT FOUND!\n", x, y);
        return null;
    }

    public boolean isPlaceable(Clickable c, int player) {
        // Checks if there are any adjacent active buttons to the given button
        boolean place = false;
        // Plus axis + of relative buttons
        int[][] locations = {
                {c.getCoords()[0], c.getCoords()[1] - 1},
                {c.getCoords()[0] - 1, c.getCoords()[1]},
                {c.getCoords()[0] + 1, c.getCoords()[1]},
                {c.getCoords()[0], c.getCoords()[1] + 1}
        };
        for (int[] location : locations) {
            Clickable b = getButton(location[0], location[1]);
            if (b != null && b.getPlayerState() >= 0 && b.getPlayerState() != player) {
                place = true;
            }
        }
        //System.out.println("\nPlace is " + place);
        return place;
    }

    public ArrayList<Clickable> getNeighbours(Clickable button) {
        ArrayList<Clickable> buttons = new ArrayList<>();
        //This array is all 8 surrounding pieces
        int[][] locations = {
                {button.getCoords()[0] - 1, button.getCoords()[1] - 1}, // top left
                {button.getCoords()[0], button.getCoords()[1] - 1},   // top
                {button.getCoords()[0] + 1, button.getCoords()[1] - 1}, // top right
                {button.getCoords()[0] - 1, button.getCoords()[1]},   // left
                {button.getCoords()[0] + 1, button.getCoords()[1]},   // right
                {button.getCoords()[0] - 1, button.getCoords()[1] + 1}, // bottom left
                {button.getCoords()[0], button.getCoords()[1] + 1},   // bottom
                {button.getCoords()[0] + 1, button.getCoords()[1] + 1}  // bottom right
        };

        for (int[] location : locations) {
            Clickable b = getButton(location[0], location[1]);
            if (b != null) {
                buttons.add(b);
            }

        }
        return buttons;
    }

    private int[] resolveCoords(Clickable A, Clickable B) {
        return new int[]{B.getCoords()[0] - A.getCoords()[0], B.getCoords()[1] - A.getCoords()[1]};
    }

    public void updateOther(ArrayList<Clickable> T, int player) {
        int col = 0;
        for(Clickable C: T){
            if(C.getPlayerState() != -1){
                otherWin.getLayout().activateDisc(7 - C.getCoords()[0],7- C.getCoords()[1],C.getPlayerState()+1);
            }

        }
    }

    public int flipDiscs(ArrayList<Clickable> T,int player){
        int count = 0;
        for(Clickable C: T){//
            count++;
            activateDisc(C.getCoords()[0],C.getCoords()[1],player+1);
        }
        return count;
    }

    public int processInput(int[] coords,int ID){

        int player =  ID > 64 ? 1 : 0;
        Clickable copyOfButton = getButton(coords[0],coords[1]);
        Clickable tempButton;
        ArrayList<Clickable> flipMe = new ArrayList<>();
        int[] xy;
        boolean flipped = false;
        System.out.println("ID = " + ID +" | Player = " + player);
        /*System.out.println("Coords are " + Arrays.toString(coords));*/
        if(toBool(player) != Main.player){
            currentWin.set_Title(String.format("|Reversi| Player %d, it is not your turn",player+1));
        }else if (isPlaceable(copyOfButton, player)) { // If there is an adjacent active tile

                /* System.out.println("Can place button");*/
                ArrayList<Clickable> neighbours = getNeighbours(getButton(coords[0], coords[1]));

                for (Clickable button : neighbours) {
                    if (button.getPlayerState() != player && button.getPlayerState() != -1) { // if the neighbour isnt the player tile

                        xy = resolveCoords(copyOfButton, button);
                        tempButton = button;

                        while (tempButton != null && tempButton.getPlayerState() != -1) {

                            if (tempButton.getPlayerState() == player) {
                                flipped = true;
                                break;
                            }
                            flipMe.add(tempButton);
                            tempButton = getButton(tempButton.getCoords()[0] + xy[0], tempButton.getCoords()[1] + xy[1]);

                        }

                        if (flipped) {
                            flipped = false;
                            activateDisc(coords[0], coords[1], player + 1);
                            int count = flipDiscs(flipMe, player);
                            Main.player = !Main.player;
                            updateOther(this.buttons, player);
                            flipMe = new ArrayList<>();
                            if(toBool(player) != Main.player) {
                                currentWin.set_Title("|Reversi| Player! it is not your turn");
                                otherWin.set_Title("|Reversi| Player! it is your turn");
                            }
                            else{
                                currentWin.set_Title("|Reversi| Player! it is your turn");
                                otherWin.set_Title("|Reversi| Player! it is not your turn");
                                }
                            endGame();
                            return count;
                            }
                        }
                    }
                }
        endGame();
        return 0;
    }

    public void endGame(){
        int none,p1,p2;
        p1 = p2 = 0;
        none = 64;
        for(Clickable C: this.buttons){
            switch (C.getPlayerState()) {
                case -1 -> none--;
                case 0 -> p1++;
                case 1 -> p2++;
                default -> {
                }
            }
        }
        int winner = p1 > p2 ? 1: 2;
        int smallest = Math.min(p1,p2);

        if(none >=64){
            Notifcation n = new Notifcation("WINNER!!!!",String.format("Player %d wins! Click on the X to end the game!",winner));
            System.out.println("END");

        }else if(smallest == 0){
            //show winner, end game
            Notifcation n = new Notifcation("WINNER!!!!",String.format("Player %d wins! Click on the X to end the game!",winner));
            System.out.println("END");
        }
    }

    public void greedyAI(int ID){ // greedy AI here
        int player =  ID > 65 ? 1 : 0;
        for(int i = 0; i < this.buttons.size();i++){
            if(isPlaceable(this.buttons.get(i),player)){
                int count = processInput(this.buttons.get(i).getCoords(),player);
                System.out.println("AI flipped "+count +" discs");
                return;
            }
        }
    }

}

