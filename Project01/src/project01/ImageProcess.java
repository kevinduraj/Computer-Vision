package project01;

import java.awt.image.BufferedImage;

public class ImageProcess {
    /*------------------------------------------------------------------------------------------------------*/
    public void computeMean(int img[][]) {

        try {
            
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = img[i][j];
                    String str = String.format("%03d ", val);
                    System.out.print(str);
                }
                System.out.println();
            }
     
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*------------------------------------------------------------------------------------------------------*/

    
}
