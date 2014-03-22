package enlarging;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    static BufferedImage image;

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        Reflection(10, 10);

        System.out.println("Original");
        displayValues("src/image/image10x10.png");
                      
        System.out.println("Vertical");
        displayValues("src/image/vertical.png");
        
        System.out.println("Horizonal");
        displayValues("src/image/horizontal.png");
        
        //Rotate180(filename);
        
    }
    /*--------------------------------------------------------------------------------------------*/
    private static void Reflection(int width, int lenght) throws IOException {
        
        String filename = "src/image/image10x10.png";
        ImageReflection ref = new ImageReflection();
        int[][] gray1 = ref.createImage(width, lenght);
        ref.writeImage(gray1, filename);
        
        FlipVerticaly(filename);
        FlipHorizontaly(filename);

        int[][]src = ref.ImageRead(filename);
        int[][]vert = ref.ImageRead("src/image/vertical.png");
        int[][]horiz = ref.ImageRead("src/image/horizontal.png");
        int[][] larger = ref.reflection(src, vert, horiz, 4);
        
        System.out.println("Reflection Image");
        ref.displayImage(larger);
        
        //int[][] gray2 = ref.reflection(gray1, 1);
        //int[][] gray3 = ref.makeReflection(gray2, 3);
        //ref.displayImage(gray3);
        //ref.writeImage(gray2, "src/image/Temp2.png");
        //int[][] gray4 = ref.flipLeft(gray1, 5);        
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void FlipVerticaly(String filename) throws IOException {

        image = ImageIO.read(new File(filename));

        // Flip the image vertically
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/vertical.png");
        ImageIO.write(bi, "png", outputfile);
    }

    /*--------------------------------------------------------------------------------------------*/
    private static void FlipHorizontaly(String filename) throws IOException {

        image = ImageIO.read(new File(filename));

        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/horizontal.png");
        ImageIO.write(bi, "png", outputfile);

    }
    /*--------------------------------------------------------------------------------------------*/

    private static void Rotate180(String filename) throws IOException {

        image = ImageIO.read(new File(filename));

        // Flip the image vertically and horizontally; equivalent to rotating the image 180 degrees
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-image.getWidth(null), -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/rotate180.png");
        ImageIO.write(bi, "png", outputfile);

    }

    /*--------------------------------------------------------------------------------------------*/
    private static void displayValues(String filename) {
        
        ImageReflection ref = new ImageReflection();
        int[][] green = ref.ImageRead(filename);
        ref.displayImage(green);
    }
    /*--------------------------------------------------------------------------------------------*/
}
