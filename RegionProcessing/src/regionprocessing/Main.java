package regionprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    private static int pixelChain = 265;

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {

        int[][] image = ImageRead("src/image/assignment05.png");
        //ImageDisplay(array);
        DurajChainCode duraj = new DurajChainCode(image);

        for (int a = 1; a < 5; a++) {
            
            pixelChain -= a*10;
            duraj.process(a, pixelChain);
            int[] first = duraj.findFirst(pixelChain);
            int[] next = new int[2];
            next[0] = first[0];
            next[1] = first[1];

            do {
                //System.out.println( "\th=" + next[0] + "\tw=" + next[1]);               
                next = duraj.getChain(next);
            } while (next[0] != 0 || next[1] != 0);

            System.out.println("--------- " + a + " ---------");
            for (String s : duraj.list) {
                System.out.println(s);
            }
        }

        System.out.println("\n\n");
        //ImageDisplay(array);
        ImageWrite("src/image/DurajChainCode.png", duraj.image);
    }
    /*--------------------------------------------------------------------------------------------*/

    private static int[][] ImageRead(String filename) throws IOException {

        File infile = new File(filename);
        BufferedImage bi = ImageIO.read(infile);

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
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void ImageWrite(String filename, int img[][]) throws IOException {

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
    }
    /*--------------------------------------------------------------------------------------------*/

    public static void ImageDisplay(int img[][]) {

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {

                if ((img[i][j] == 1) || (img[i][j] == pixelChain)) {
                    System.out.format("%1d ", img[i][j]);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/
}
