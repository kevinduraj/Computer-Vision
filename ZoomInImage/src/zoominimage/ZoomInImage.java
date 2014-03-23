package zoominimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ZoomInImage {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {
            ZoomOnImage();
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
