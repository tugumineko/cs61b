import edu.princeton.cs.algs4.Picture;

import java.util.ArrayList;
import java.util.Arrays;

public class SeamCarver {

    private final Picture picture;

    public SeamCarver(Picture picture){
        this.picture = picture;
    }

    //current picture
    public Picture picture(){
        return picture;
    }

    //width of current picture
    public int width(){
        return picture.width();
    }

    //height of current picture
    public int height(){
        return picture.height();
    }

    private int myAbs(int a,int b){
        return a-b<0?b-a:a-b;
    }

    private int delta(int n1, int n2){
        int blue = myAbs((n1&0xff) , (n2&0xff));
        int green = myAbs((n1&0xff00)>>8,(n2&0xff00)>>8);
        int red = myAbs((n1&0xff0000)>>16,(n2&0xff0000)>>16);
        return blue*blue+green*green+red*red;
    }

    //energy of pixel at column x and row y
    public double energy(int x, int y){

        int colorX1 = x-1<0 ? picture.getRGB(width()-1,y):picture.getRGB(x-1,y);
        int colorX2 = x+1>width()-1 ? picture.getRGB(0,y):picture.getRGB(x+1,y);
        int deltaX = delta(colorX1,colorX2);

        int colorY1 = y-1<0 ? picture.getRGB(x,height()-1):picture.getRGB(x,y-1);
        int colorY2 = y+1>height()-1 ? picture.getRGB(x,0):picture.getRGB(x,y+1);
        int deltaY = delta(colorY1,colorY2);

        return (double) (deltaX+deltaY);
    }

    //sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return findSeamHelper(height(), width(), true);
    }

    //sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return findSeamHelper(width(), height(), false);
    }

    private int[] findSeamHelper(int w, int h, boolean mode) {
        int[] ans = new int[h];
        if (w == 1) {
            Arrays.fill(ans, 0);
            return ans;
        }

        double[][] M = new double[h][w];
        int[][] path = new int[h][w];
        for (int y = 0; y < h; ++y) {
            if (y == 0) {
                for (int x = 0; x < w; ++x) {
                    M[y][x] = (mode) ? energy(y, x) : energy(x, y);
                }
            } else {
                for (int x = 0; x < w; ++x) {
                    double e = (mode) ? energy(y, x) : energy(x, y);
                    if (x == 0) {
                        if (M[y - 1][x] < M[y - 1][x + 1]) {
                            M[y][x] = M[y - 1][x] + e;
                            path[y][x] = x;
                        } else {
                            M[y][x] = M[y - 1][x + 1] + e;
                            path[y][x] = x + 1;
                        }
                    } else if (x < w - 1) {
                        double minVal = 0.0;
                        int pos = x;
                        if (M[y - 1][x] < M[y - 1][x - 1]) {
                            minVal = M[y - 1][x];
                        } else {
                            minVal = M[y - 1][x - 1];
                            pos = x - 1;
                        }
                        if (minVal > M[y - 1][x + 1]) {
                            minVal = M[y - 1][x + 1];
                            pos = x + 1;
                        }
                        M[y][x] = minVal + e;
                        path[y][x] = pos;
                    } else {
                        if (M[y - 1][x] < M[y - 1][x - 1]) {
                            M[y][x] = M[y - 1][x] + e;
                            path[y][x] = x;
                        } else {
                            M[y][x] = M[y - 1][x - 1] + e;
                            path[y][x] = x - 1;
                        }
                    }
                }
            }
        }
        int hIndex = h - 1;
        int wIndex = 0;
        double minVal = Double.MAX_VALUE;
        for (int i = 0; i < w; ++i) {
            if (minVal > M[h - 1][i]) {
                minVal = M[h - 1][i];
                wIndex = i;
            }
        }
        while (hIndex >= 0) {
            ans[hIndex] = wIndex;
            wIndex = path[hIndex][wIndex];
            hIndex--;
        }
        return ans;
    }


    //remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam){
        isValidSeam(seam,width());
        SeamRemover.removeHorizontalSeam(picture,seam);
    }

    //remove vertical seam from picture
    public void removeVerticalSeam(int[] seam){
        isValidSeam(seam,height());
        SeamRemover.removeVerticalSeam(picture,seam);
    }

    private void isValidSeam(int[] seam,int len){
        if(seam.length!=len)
            throw new IllegalArgumentException();
        for(int i=1;i<len;i++)
            if(myAbs(seam[i],seam[i-1])>1){
                throw new IllegalArgumentException();
            }
    }

}
