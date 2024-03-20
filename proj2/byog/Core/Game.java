package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */

    private Random rand;
    private long seed;
    private int locationX;
    private int locationY;
    private int playerX;
    private int playerY;
    private int step = 0;
    private StringBuffer health = new StringBuffer("♥♥♥♥♥");
    private int healthNum =4;

    public static final int WIDTH = 60;
    public static final int HEIGHT = 40;

    private TETile[][] world = new TETile[WIDTH][HEIGHT];

    private  Font bigFont = new Font("Monaco",Font.BOLD,40);
    private Font smallFont = new Font("Monaco", Font.PLAIN,20);

    public void settingBackground(){
        StdDraw.setCanvasSize(WIDTH*16,HEIGHT*16);
        Font font = new Font("Monaco",Font.BOLD,30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0,WIDTH);
        StdDraw.setYscale(0,HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    public void drawMainMenu(){
        settingBackground();

        int midWidth = WIDTH/2;
        int midHeight = HEIGHT/2;
        int twoInThreeHeight = 2*HEIGHT/3;

        // Draw the actual text
        StdDraw.setPenColor(Color.WHITE);

        StdDraw.setFont(bigFont);
        StdDraw.text(midWidth,twoInThreeHeight,"THE GAME");

        StdDraw.setFont(smallFont);
        StdDraw.text(midWidth,midHeight,"New Game (N)");
        StdDraw.text(midWidth,midHeight-2,"Load Game (L)");
        StdDraw.text(midWidth,midHeight-4,"Quit (Q)");
        StdDraw.show();
        operationOption();
    }

    public void operationOption(){
        while(true) {
            if(!StdDraw.hasNextKeyTyped()){
                continue;
            }
            char op = StdDraw.nextKeyTyped();
            switch (op) {
                case 'N', 'n':
                    drawSeedInput();
                    break;
                case 'L', 'l':
                    loadGame();
                    break;
                case 'Q', 'q':
                    quit();
                    break;
                default:
            }
        }
    }

    public void drawSeedInput(){

        //clear
        StdDraw.clear(Color.BLACK);

        int midWidth = WIDTH/2;
        int midHeight = HEIGHT/2;

        StdDraw.setFont(bigFont);
        StdDraw.text(midWidth,midHeight+4,"Please input your SEED: ");
        StdDraw.setFont(smallFont);
        StdDraw.text(midWidth,midHeight+2, "(Enter the 'Enter' key to continue)");
        StdDraw.show();

        //show the picture of input SEED
        String input = "";
        while(true) {
            if(!StdDraw.hasNextKeyTyped())
                continue;
            char key = StdDraw.nextKeyTyped();
            if(key == '\n'){
                seed = Long.parseLong(input);
                rand = new Random(seed);
                StdDraw.clear(Color.BLACK);
                generateWorld();
            }
            if(key < 48||key > 57)continue;
            input += String.valueOf(key);
            StdDraw.clear(Color.BLACK);

            //redisplay
            StdDraw.setFont(bigFont);
            StdDraw.text(midWidth,midHeight+4,"Please input your SEED: ");
            StdDraw.setFont(smallFont);
            StdDraw.text(midWidth,midHeight+2, "(Enter the 'Enter' key to continue)");
            StdDraw.text(midWidth,midHeight,input);
            StdDraw.show();
        }
    }

    public void loadGame(){
        return;
    }

    public void quit(){
        System.exit(0);
    }

    /**
     * generate the game world.
     * Only use the generateWorld method is OK.
     * */
    public void generateWorld(){
        ter.initialize(WIDTH,HEIGHT+10,0,5);
        initialize();
        generateLocation();
        for(int i = 1;i<=100;i++) {
            if(locationX>=WIDTH-1||locationY>=HEIGHT-1)break;
            int op = rand.nextInt(2);
            switch (op) {
                case 0:
                    generateRoom();
                case 1:
                    generateHallways();
            }
        }
        generateWall();
        generateDoor();
        generateUI();
        //ter.renderFrame(world);
        playWithCharacter();
    }

    public void generateUI(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.line(0,HEIGHT-5,WIDTH,HEIGHT-5);
        StdDraw.line(0,5,WIDTH,5);
        StdDraw.setFont(smallFont);
        StdDraw.text(1,2,"Use W A S D to move the character!NOTICE don't touch the wall!");
        StdDraw.text(1,HEIGHT+7,String.valueOf(health));
        StdDraw.text(WIDTH-5,HEIGHT+7,"STEP: "+step);
        StdDraw.show();
    }

    public void initialize(){
        for(int x = 0;x<WIDTH;x++)
            for(int y = 0;y<HEIGHT;y++)
                world[x][y] = TETile.colorVariant(Tileset.NOTHING,32,32,32,rand);
    }

    public void generateLocation(){
        /**
         * For room,it is the left corner;
         * For hallways, it is the begining.
         * */
        int x = 2;
        int y = 2;
        locationX = x;
        locationY = y;
    }

    public void generateRoom(){
        int weight = rand.nextInt(1,5);
        int height = rand.nextInt(1,5);
        for(int i=locationX;i<=locationX+weight;i++)
            for(int j=locationY;j<=locationY+height;j++)
                if(inMap(i,j))
                    world[i][j] = Tileset.FLOOR;
        locationX+=weight;
        locationY+=height;
    }

    public void generateHallways(){
        int flag = rand.nextInt(4);
        switch (flag){
            case 0:
                if(locationY>=HEIGHT-5)
                    generateHallwaysDown();
                generateHallwaysUp();
                break;
            case 1:
                if(locationY<=5)
                    generateHallwaysUp();
                generateHallwaysDown();
                break;
            case 2:
                if (locationX <= 5) {
                    int op = rand.nextInt(2);
                    switch (op) {
                        case 0:
                            generateHallwaysUp();
                            break;
                        case 1:
                            generateHallwaysDown();
                            break;
                    }
                }
                generateHallwaysLeft();
                break;
            case 3:
                if (locationX >= WIDTH-5) {
                    int op = rand.nextInt(2);
                    switch (op) {
                        case 0:
                            generateHallwaysUp();
                            break;
                        case 1:
                            generateHallwaysDown();
                            break;
                    }
                }
                generateHallwaysRight();
                break;
        }
    }

    public void generateHallwaysLeft(){
        int length = rand.nextInt(1,5);
        for(int i = locationX;i>=locationX-length;i--)
            if(inMap(i,locationY))
                world[i][locationY] = Tileset.FLOOR;
        locationX=locationX-length;
    }

    public void generateHallwaysRight(){
        int length = rand.nextInt(1,5);
        for(int i = locationX;i<=locationX+length;i++)
            if(inMap(i,locationY))
                world[i][locationY] = Tileset.FLOOR;
        locationX=locationX+length;
    }

    public void generateHallwaysUp(){
        int length = rand.nextInt(1,5);
        for(int i = locationY;i<=locationY+length;i++)
            if(inMap(locationX,i))
                world[locationX][i] = Tileset.FLOOR;
        locationY=locationY+length;
    }

    public void generateHallwaysDown(){
        int length = rand.nextInt(1,5);
        for(int i = locationY;i>=locationY-length;i--)
            if(inMap(locationX,i))
                world[locationX][i] = Tileset.FLOOR;
        locationY=locationY-length;
    }

    public boolean checkFloor(int x,int y,TETile[][] world){
        int[] dx={-1,1,0,0};
        int[] dy={0,0,1,-1};
        for(int i=0;i<4;i++){
            if(world[x][y]==Tileset.FLOOR)break;
            int tx =x+dx[i];int ty =y+dy[i];
            if(inMap(tx,ty)&&world[tx][ty]==Tileset.FLOOR)
                return true;
        }
        return false;
    }

    public boolean inMap(int x,int y){
        return x>0&&x<WIDTH-1&&y>0&&y<HEIGHT-1;
    }

    public void generateWall() {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++) {
                if (checkFloor(i, j, world))
                    world[i][j] = TETile.colorVariant(Tileset.WALL, 32, 32, 32, rand);
            }
    }

    public void generateDoor() {
        for (int i = WIDTH-1; i > 0; i--)
            for (int j = HEIGHT-1; j > 0; j--)
                if (checkFloor(i, j, world))
                {
                    world[i][j] = Tileset.LOCKED_DOOR;
                    return;
                }
    }
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */

    public void playWithCharacter(){
        world[2][2]=Tileset.PLAYER;
        ter.renderFrame(world);
        playerX=2;
        playerY=2;
        int tx=2,ty=2;
        while(true){
            if(!StdDraw.hasNextKeyTyped())
                continue;
            if(healthNum==-1)loseTheGame();
            char key = StdDraw.nextKeyTyped();
            switch (key){
                case 'w','W':
                    ty = playerY+1;
                    if(world[playerX][ty]==Tileset.LOCKED_DOOR)winTheGame();
                    if(world[playerX][ty]!=Tileset.FLOOR){
                        health.deleteCharAt(healthNum);
                        healthNum--;
                        continue;
                    }
                    world[playerX][ty]=Tileset.PLAYER;
                    world[playerX][playerY]=Tileset.FLOOR;
                    playerY=ty;
                    step++;
                    break;
                case 's','S':
                    ty = playerY-1;
                    if(world[playerX][ty]==Tileset.LOCKED_DOOR)winTheGame();
                    if(world[playerX][ty]!=Tileset.FLOOR){
                        health.deleteCharAt(healthNum);
                        healthNum--;
                        continue;
                    }
                    world[playerX][ty]=Tileset.PLAYER;
                    world[playerX][playerY]=Tileset.FLOOR;
                    playerY=ty;
                    step++;
                    break;
                case 'a','A':
                    tx = playerX-1;
                    if(world[tx][playerY]==Tileset.LOCKED_DOOR)winTheGame();
                    if(world[tx][playerY]!=Tileset.FLOOR){
                        health.deleteCharAt(healthNum);
                        healthNum--;
                        continue;
                    }
                    world[tx][playerY]=Tileset.PLAYER;
                    world[playerX][playerY]=Tileset.FLOOR;
                    playerX=tx;
                    step++;
                    break;
                case 'd','D':
                    tx = playerX+1;
                    if(world[tx][playerY]==Tileset.LOCKED_DOOR)winTheGame();
                    if(world[tx][playerY]!=Tileset.FLOOR){
                        health.deleteCharAt(healthNum);
                        healthNum--;
                        continue;
                    }
                    world[tx][playerY]=Tileset.PLAYER;
                    world[playerX][playerY]=Tileset.FLOOR;
                    playerX=tx;
                    step++;
                    break;
            }
            StdDraw.show();
            ter.renderFrame(world);
        }
    }

    public void winTheGame(){
        ter.initialize(WIDTH,HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH/2,HEIGHT/2+3, "Congratulations!");
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH/2,HEIGHT/2,"You win the game!");
        StdDraw.setFont(smallFont);
        StdDraw.text(WIDTH/2,HEIGHT/2-2, "Thanks for playing!");
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH/2,HEIGHT/2-10,"STEP: " +step);
        StdDraw.setFont(smallFont);
        StdDraw.text(WIDTH/2,HEIGHT/2-12,"SEED: " +seed);
        StdDraw.show();
    }

    public void loseTheGame() {
        ter.initialize(WIDTH, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH / 2, HEIGHT / 2 + 3, "Damn!");
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "You regretfully lose the game!");
        StdDraw.setFont(smallFont);
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, "Don't be sad.Enter R to have a retry");
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH/2,HEIGHT/2-10,"STEP: " +step);
        StdDraw.setFont(smallFont);
        StdDraw.text(WIDTH/2,HEIGHT/2-12,"SEED: " +seed);
        StdDraw.show();
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char op = StdDraw.nextKeyTyped();
            if (op == 'r' || op == 'R') {
                StdDraw.clear(Color.BLACK);
                rand = new Random(seed);
                health = new StringBuffer("♥♥♥♥♥");
                healthNum =4;
                generateWorld();
            }
        }
    }

    public void showGUI(){
        //
    }

    public void playWithKeyboard() {
        drawMainMenu();
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
