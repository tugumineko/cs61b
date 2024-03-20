package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.seedInput(seed);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random();
    }

    public void seedInput(int n){
        rand.setSeed(n);
    }

    public String randomEncourgement() {
        switch (rand.nextInt(3)) {
            case 0:
                return ENCOURAGEMENT[0];
            case 1:
                return ENCOURAGEMENT[1];
            case 2:
                return ENCOURAGEMENT[2];
            default:
        }
        return null;
    }
    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        char s[] = null;
        for(int i=0;i<n;i++) {
            s[i]=CHARACTERS[rand.nextInt(27)];
        }
        return s.toString();
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.line(0,0.1,1,0.1);
        StdDraw.text(0.05,0.05,"Round: "+round);
        StdDraw.text(0.5,0.05,"Watch!");
        StdDraw.text(0.95,0.05,randomEncourgement());
        StdDraw.show();
        flashSequence(s);
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char c[]=letters.toCharArray();
        for(char i :c)
        {
            StdDraw.text(0.5,0.5, String.valueOf(i));
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear();
            StdDraw.pause(500);
        }

    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        char s[] = null;
        for(int i=0;i<n;i++){
            if(StdDraw.hasNextKeyTyped())
                s[i]=StdDraw.nextKeyTyped();
        }
        return s.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round=1;String stringOutput,stringInput;
        while(true) {
            StdDraw.text(0.5, 0.5, "Round: " + round);
            StdDraw.show();
            StdDraw.pause(1000);
            StdDraw.clear();
            stringOutput = generateRandomString(round);
            drawFrame(stringOutput);
            stringInput = solicitNCharsInput(round);
            if (stringInput.equals(stringOutput)) round++;
            else System.out.println("Game Over! You made it to round:"+round);
        }
        //TODO: Establish Game loop
    }

}
