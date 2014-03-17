package filter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class MedianFilter1 {

    public void process() throws IOException {
        
        int kernel = 9;
        File f = new File("src/image/Lenna.png");
        Color[] pixel = new Color[kernel];
        int[] R = new int[kernel];
        int[] B = new int[kernel];
        int[] G = new int[kernel];

        File output = new File("src/image/Lenna1.png");
        BufferedImage img = ImageIO.read(f);

        for (int i = 1; i < img.getWidth() - 1; i++) {
            for (int j = 1; j < img.getHeight() - 1; j++) {

                pixel[0] = new Color(img.getRGB(i - 1, j - 1));
                pixel[1] = new Color(img.getRGB(i - 1, j));
                pixel[2] = new Color(img.getRGB(i - 1, j + 1));

                pixel[3] = new Color(img.getRGB(i, j + 1));
                pixel[4] = new Color(img.getRGB(i + 1, j + 1));
                pixel[5] = new Color(img.getRGB(i + 1, j));

                pixel[6] = new Color(img.getRGB(i + 1, j - 1));
                pixel[7] = new Color(img.getRGB(i, j - 1));
                pixel[8] = new Color(img.getRGB(i, j));

                for (int k = 0; k < kernel; k++) {
                    R[k] = pixel[k].getRed();
                    B[k] = pixel[k].getBlue();
                    G[k] = pixel[k].getGreen();
                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);

                img.setRGB(i, j, new Color(R[4], B[4], G[4]).getRGB());
            }
        }
        ImageIO.write(img, "jpg", output);
    }
}
