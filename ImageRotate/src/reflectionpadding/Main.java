package reflectionpadding;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    private static String input = "src/image/Lenna.png";
    private static String border = "src/image/Padded.png";
    private static String output = "C:\\Users\\durajkt\\Desktop\\Assign3b\\reflection.png";
    private static int size = 7;
            
    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {
       
        //input = "src/image/Test.png";
        //CreateTestImage(input);
        Padding padding = new Padding();
        int[][] out = padding.add(input, size);   
        ImageWrite(out, "src/image/padded.png"); 
        int[][] finalImage = Reflection(size);
        //ImageDisplay(finalImage);
        
        
        
    }
    /*--------------------------------------------------------------------------------------------*/

    private static int[][] Reflection(int size) throws IOException {

        Rotate ref = new Rotate(input);
        int[][] orig = ref.ImageRead(input);        
        int[][] padded = ref.ImageRead(border); 
        
        int[][] vert = ref.ImageRead("src/image/vertical.png"); 
        //System.out.println("Vertical");
        //ImageDisplay(vert);
        
        int[][] horiz = ref.ImageRead("src/image/horizontal.png");
        //System.out.println("Horizontal");
        //ImageDisplay(horiz);

        int[][] rotate = ref.ImageRead("src/image/rotate180.png");
        //System.out.println("Rotate");
        //ImageDisplay(rotate);

        padded = ref.reflection(padded, orig, vert, horiz, rotate, size);
        ImageWrite(padded, output);
        return padded;
        
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void CreateTestImage(String input) throws IOException {

        int width, height;
        width = height = 15;

        int gray[][] = new int[width][height];
        int counter = 0;

        for (int i = 0; i < gray.length; ++i) {
            for (int j = 0; j < gray[i].length; ++j) {

                gray[i][j] = counter++;
            }
        }

        ImageWrite(gray, input);
        ImageDisplay(gray);

    }
    /*--------------------------------------------------------------------------------------------*/

    public static void ImageWrite(int img[][], String filename) {

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
            //File outputfile = new File(System.getProperty("user.home"), "Desktop");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public static void ImageDisplay(int img[][]) {

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {

                System.out.format("%3d ", img[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/
}
