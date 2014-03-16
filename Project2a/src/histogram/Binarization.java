package histogram;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Binarization {
    
    /*----------------------------------------------------------------------------------------------
     Binarize Buffered Image
     ----------------------------------------------------------------------------------------------*/

    public void binarize(int img[][], int threshold, String filename) {

        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            // -- prepare output image
            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {

                    int val = img[i][j];

                    if (val > threshold) {
                        val = 255;
                    } else {
                        val = 0;
                    }

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

}
