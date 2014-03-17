package enlarging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enlarging {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {
        //ZoomOnImage();
        int width=10;
        int lenght=10;
        
        ImageReflection ref = new ImageReflection();
        int[][] gray1 = ref.createImage(width,lenght);
        ref.displayImage(gray1);
        ref.writeImage(gray1, "src/image/Temp1.png");
        
        int[][] gray2 = ref.reflection(gray1, 1);
        int[][] gray3 = ref.makeReflection(gray2);
        ref.displayImage(gray3);
        //ref.writeImage(gray2, "src/image/Temp2.png");        
        
    }    
    
    /*--------------------------------------------------------------------------------------------*/    
    private static void ZoomOnImage() {
        BufferedImage image1 = null;
        BufferedImage image2 = null;

        try {
            image1 = ImageIO.read(new File("src/image/Lenna.png"));
            
            ImageZoomIn zoom = new ImageZoomIn();
            image2 = zoom.enlarge(image1, 2);
            zoom.WriteImage(image2, "src/image/Lenna1.jpg");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/    
}
