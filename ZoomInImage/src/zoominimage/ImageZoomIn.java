package zoominimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageZoomIn {
    
    /*--------------------------------------------------------------------------------------------*/
    public  BufferedImage enlarge(BufferedImage image, int n) {

        int w = n + image.getWidth();
        int h = n + image.getHeight();

        BufferedImage enlargedImage = new BufferedImage(w, h, image.getType());

        for (int y = 0; y < h; ++y) {
            for (int x = 0; x < w; ++x) {
                enlargedImage.setRGB(x, y, image.getRGB(x / n, y / n));
            }
        }
        return enlargedImage;
    }
    /*--------------------------------------------------------------------------------------------*/
    public void WriteImage(BufferedImage bi, String filename) {
        
        try {
            System.out.println("Writting Image: " + filename);
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/
}
