package imagefilter;

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
                } else if ((i > 0) && (j == img.length-1)) {
                    System.out.format("%3d ", img[i][j]);
                }
                else {
                    System.out.format("%3s ", "x");
                }                
            }

            System.out.println();

        }
        System.out.println();
    }
   /*--------------------------------------------------------------------------------------------*/
    public int[][] makeReflection(int img[][], int kernel) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                //---- Top ----//
                if (i == 0) {
                    int a = i+2;
                    if(a>9) a=9;
                    img[i][j] = img[a][j];
                    
                } //---- Left ----//
                else if ((i != 0) && (j == 0)) {
                    int a = j+2;
                    img[i][j] = img[i][a];
                    
                } //---- Bottom -----//
                else if (i == img.length - 1) {
                    int a = i-2;
                    img[i][j] = img[a][j];
                    
                } //---- Right ----//
                else if ((i > 0) && (j == img.length-1)) {
                    int a = j-2;
                    img[i][j] = img[i][a];
                    
                }
                //----- inside ----//
                else {
                    //System.out.format("%3s ", "x");
                    //System.out.format("%3d ", img[i][j]);
                }
            }
        }
        
        //--- LeftTop Corner ---//
        img[0][0] = img[kernel-1][kernel-1];
        
        //---- LeftBottom Corner ---//
        img[img.length-1][0] = img[img.length-1][kernel-1];

        //--- RightTop Corner ---//
        img[0][img.length-1] = img[kernel-1][img.length-1];

        //--- RightBottom Corner ---//
        img[img.length-1][img.length-1] = img[img.length-3][img.length-3];   
        
        return img;
    }    
    
    /*--------------------------------------------------------------------------------------------*/
    public void displayReflection(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                //---- Top ---//
                if (i == 0) {
                    int a = i+2;
                    if(a>9) a=9;
                    System.out.format("%3d ",img[a][j]);
                    
                } //--- Left ----//
                else if ((i != 0) && (j == 0)) {
                    int a = j+2;
                    System.out.format("%3d ", img[i][a]);
                    
                } //--- Bottom -----//
                else if (i == img.length - 1) {
                    int a = i-2;
                    System.out.format("%3d ", img[a][j]);
                    
                } //---- Right ----//
                else if ((i > 0) && (j == img.length-1)) {
                    int a = j-2;
                    System.out.format("%3d ", img[i][a]);
                    
                }
                //----- inside ----//
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
