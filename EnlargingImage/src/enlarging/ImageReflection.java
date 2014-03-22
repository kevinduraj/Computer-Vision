package enlarging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImageReflection {

    /*--------------------------------------------------------------------------------------------*/
    public int[][] createImage(int width, int height) {

        int gray[][] = new int[width][height];
        int counter = 0;

        for (int i = 0; i < gray.length; ++i) {
            for (int j = 0; j < gray[i].length; ++j) {

                gray[i][j] = counter++;
            }
        }
        return gray;
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] reflection(int img[][], int size) {

        int gray[][] = new int[img.length + size * 2][img[0].length + size * 2];

        for (int i = size; i < gray.length - size; ++i) {
            for (int j = size; j < gray[i].length - size; ++j) {

                gray[i][j] = img[i - size][j - size];
            }
        }
        return gray;
    }

    /*--------------------------------------------------------------------------------------------*/
    public void displayImage(int img[][]) {

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {

                System.out.format("%3d ", img[i][j]);

            }
            System.out.println();
        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    public void displayBorder(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                // horizontal
                if (i == 0) {
                    System.out.format("%3d ", img[i][j]);
                } // vertical;
                else if ((i != 0) && (j == 0)) {
                    System.out.format("%3d ", img[i][j]);
                } else if (i == img.length - 1) {
                    System.out.format("%3d ", img[i][j]);
                } else if ((i > 0) && (j == img.length - 1)) {
                    System.out.format("%3d ", img[i][j]);
                } else {
                    System.out.format("%3s ", "x");
                }
            }

            System.out.println();

        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    int[][] flipLeft(int[][] source, int kernel) {

        int size = kernel / 2;
        int img[][] = new int[source.length + size][source[0].length + size];

        //for (int i = 0; i < source.length; i++) {
        for (int i = source.length - 1; i >= 0; i--) {
            //for(int j = source[0].length - 1; j>=0; j--) {
            for (int j = 0; j < source[i].length; j++) {
                System.out.format("%3d ", source[i][j]);
            }
            System.out.println();
        }

        return img;
    }
    /*--------------------------------------------------------------------------------------------*/

    int[][] flipOver(int[][] source, int kernel) {

        int size = kernel / 2;
        int img[][] = new int[source.length + size][source[0].length + size];

        for (int i = source.length - 1; i >= 0; i--) {
            for (int j = source[0].length - 1; j >= 0; j--) {
                System.out.format("%3d ", source[i][j]);
            }
            System.out.println();
        }

        return img;
    }

    /*--------------------------------------------------------------------------------------------*/
    private int[][] fillLarger(int src[][], int size) {

        int[][] img = new int[src.length + size][src[0].length + size];

        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[i].length; j++) {
                img[i + size / 2][j + size / 2] = src[i][j];
            }
        }

        return img;
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] reflection(int src[][], int vert[][], int horiz[][], int size) {
        //size = 4
        int oneSide = size / 2; // =  2
        int height1 = src.length;
        int width = src[0].length;

        int[][] img = fillLarger(src, size);
        int height2 = img.length;
        int width2 = img[0].length;
        
        //--- Left Side
        for (int i = size - 1; i <= height1 + 1; i++) { // horizontal
            for (int j = 0; j <= 1; j++) { // vertical
                int res = 1 - j;
                img[i][j] = horiz[i - oneSide][width - res - 1];
            }
        }

        //--- Right Side
        for (int i = 12; i < 14; i++) { // horizontal
            for (int j = 2; j <= height1 + 1; j++) { // vertical                
                img[j][i] = horiz[j - oneSide][i - 12];
            }
        }

        return img;
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] makeReflection(int[][] src, int kernel) {

        int size = kernel / 2;
        for (int i = 0; i < src.length; i++) {

            for (int j = 0; j < src[i].length; j++) {

                //---- Top ----//
                if (i == 0) {
                    int a = i + size;
                    if (a > src.length) {
                        a = src.length;
                    }
                    src[i][j] = src[a][j];

                } //---- Left ----//
                else if ((i != 0) && (j == 0)) {
                    int a = j + size;
                    src[i][j] = src[i][a];

                } //---- Bottom -----//
                else if (i == src.length - 1) {
                    int a = i - size;
                    src[i][j] = src[a][j];

                } //---- Right ----//
                else if ((i > 0) && (j == src.length - 1)) {
                    int a = j - size;
                    src[i][j] = src[i][a];

                } //----- inside ----//
                else {
                    //System.out.format("%3s ", "x");
                    //System.out.format("%3d ", img[i][j]);
                }
            }
        }

        //--- LeftTop Corner ---//
        src[0][0] = src[size][size];

        //---- LeftBottom Corner ---//
        src[src.length - 1][0] = src[src.length - 1][size];

        //--- RightTop Corner ---//
        src[0][src.length - 1] = src[size][src.length - 1];
        //--- RightBottom Corner ---//
        src[src.length - 1][src.length - 1] = src[src.length - size][src.length - size];
        return src;
    }

    /*--------------------------------------------------------------------------------------------*/
    public void displayReflection(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                //---- Top ---//
                if (i == 0) {
                    int a = i + 2;
                    if (a > 9) {
                        a = 9;
                    }
                    System.out.format("%3d ", img[a][j]);

                } //--- Left ----//
                else if ((i != 0) && (j == 0)) {
                    int a = j + 2;
                    System.out.format("%3d ", img[i][a]);

                } //--- Bottom -----//
                else if (i == img.length - 1) {
                    int a = i - 2;
                    System.out.format("%3d ", img[a][j]);

                } //---- Right ----//
                else if ((i > 0) && (j == img.length - 1)) {
                    int a = j - 2;
                    System.out.format("%3d ", img[i][a]);

                } //----- inside ----//
                else {
                    //System.out.format("%3s ", "x");
                    System.out.format("%3d ", img[i][j]);
                }
            }

            System.out.println();

        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] ImageRead(String filename) {

        try {

            // -- read input image
            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            // -- separate image into RGB components
            int red[][] = new int[bi.getHeight()][bi.getWidth()];
            int grn[][] = new int[bi.getHeight()][bi.getWidth()];
            int blu[][] = new int[bi.getHeight()][bi.getWidth()];
            for (int i = 0; i < red.length; ++i) {
                for (int j = 0; j < red[i].length; ++j) {
                    red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    blu[i][j] = bi.getRGB(j, i) & 0xFF;
                }
            }

            return grn;

        } catch (IOException e) {
            System.out.println("image I/O error");
            return null;
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public void writeImage(int img[][], String filename) {

        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = img[i][j];
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

}
