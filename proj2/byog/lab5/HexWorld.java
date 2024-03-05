package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;
    private static final long SEED = 2088;
    private static final Random RANDOM = new Random(SEED);

    private int size;
    public static class Position{
        private int x;
        private int y;

        public Position(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.GRASS;
            default: return Tileset.MOUNTAIN;
        }
    }

    public static void addHexagon(TETile[][] world,int size,Position p,TETile t){
        int start = size;
        int num = size;
        int num2 = size;
        int xCoord,yCoord = p.y;
        while(num2--!=0) {
            for (int i = 1; i <= size * 2; i++) {
                xCoord=p.x+start;
                if (i == start) {
                    for(int j=1;j<=num;j++){
                        xCoord++;
                        world[xCoord][yCoord] = TETile.colorVariant(t,32,32,32,RANDOM);
                        i++;
                    }
                    start--;
                    num += 2;
                }
            }
            yCoord+=1;
        }
        num2 = size;
        start++;
        num-=2;
        while(num2--!=0) {
            xCoord=p.x+start;
            for (int i = 1; i <= size * 2; i++) {
                if (i == start) {
                    for(int j=1;j<=num;j++) {
                        xCoord++;
                        world[xCoord][yCoord] = TETile.colorVariant(t,32,32,32,RANDOM);
                        i++;
                    }
                    start++;
                    num -= 2;
                }
            }
            yCoord+=1;
        }
    }

    public static void drawHexagonWorld(TETile[][] world,int size,Position p){
        int width = 3*size-2;
        HexWorld.addHexagon(world,size,p,randomTile());
        p.x-=2*size-1;p.y-=size;
        for(int q=0;q<3;q++) {
            HexWorld.addHexagon(world, size, p, randomTile());
            p.x += size + width ;
            HexWorld.addHexagon(world, size, p, randomTile());
            p.x -= 3 * size + width - 1;//namely 6*size-3
            p.y -= size;
            Position temp = new Position(p.x, p.y);
            for (int i = 0; i < 3; i++) {
                HexWorld.addHexagon(world, size, temp, randomTile());
                temp.x += width + size ;
            }
            p.y -= size;
            p.x += 2 * size - 1;
        }
        HexWorld.addHexagon(world, size, p, randomTile());
        p.x += size + width ;
        HexWorld.addHexagon(world, size, p, randomTile());
        p.x-=2*size-1;p.y-=size;
        HexWorld.addHexagon(world, size, p, randomTile());
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH,HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for(int i=0;i<WIDTH;i++)
            for(int j=0;j<HEIGHT;j++)
                world[i][j]=Tileset.NOTHING;

        Position p = new Position(30,50);
        int size=2;
        HexWorld.drawHexagonWorld(world,size,p);
        ter.renderFrame(world);
    }
}

