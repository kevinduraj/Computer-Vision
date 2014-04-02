package fullreflection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    private static final int padding_x = 801;
    private static final int padding_y = 3;
    
    public static void main(String[] args) throws IOException {

        int[][] iimage = ImageRead("src/image/A_Victoria.png");
        Reflection ref = new Reflection();
        
        int[][] oimage = ref.convolve(iimage, padding_x, padding_y);  // must be odd number
        ImageWrite(oimage, "src/image/reflection.png");
        
        //int[][] simage = ScaleDown(oimage,padding_x,padding_y);
        //ImageWrite(simage, "src/image/scaledown.png");

    }

    /*--------------------------------------------------------------------------------------------*/

    public static int[][] ImageRead(String filename) {

        try {
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
    public static void ImageWrite(int img[][], String filename) {
        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            // -- prepare output image
            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = img[i][j];
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            // -- write output image
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {

        }
    }
    /*--------------------------------------------------------------------------------------------*/
    public static int[][] ScaleDown(double[][] paddedImg, int x_size, int y_size) {
        
        int height=paddedImg.length-y_size+1;
        int width=paddedImg[0].length-x_size+1;
        
        int half_y=y_size/2;
        int half_x=x_size/2;
        
        int[][] scaledDownImg = new int[height][width];
        
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {                                
                scaledDownImg[i][j] = (int) paddedImg[half_y+i][half_x+j];
            }
        }
        
        return scaledDownImg;
        
    }
    /*--------------------------------------------------------------------------------------------*/     
}
