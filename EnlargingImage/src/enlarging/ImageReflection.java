package enlarging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReflection {

    /*--------------------------------------------------------------------------------------------*/
    public  int[][] createImage(int width, int height) {

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
    public  int[][] reflection(int img[][], int size) {

        int gray[][] = new int[img.length+size*2][img[0].length+size*2];

        for (int i = size; i < gray.length-size; ++i) {
            for (int j = size; j < gray[i].length-size; ++j) {

                gray[i][j] = img[i-size][j-size];
            }
        }
        return gray;
    }
    
    /*--------------------------------------------------------------------------------------------*/
    public void displayImage(int img[][]) {

            for(int i=0; i<img.length; i++) {
                for(int j=0; j<img[i].length; j++) {
                    
                   System.out.format("%3d ", img[i][j]);
                   
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
