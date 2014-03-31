package processingpipeline;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    private static final String sInput = "src/image/Lenna.png";
   
    /*--------------------------------------------------------------------------------------------*/        
    public static void main(String[] args) throws IOException {

        System.out.println("Processing Pipeline");
        
        /*------------------- Percentile -----------------------*/
        int[][] imgPrecentile = ProcessPrecentile();
        
        Reflection ref = new Reflection();        
        int[][] imgReflection = ref.conv(imgPrecentile, 7, 7);  // must be odd number                       
        //ImageWrite("src/image/reflection.png", imgReflection);
        
        /*------------------ Median Filter ---------------------*/
        MedianFilter median = new MedianFilter();
        int[][] imgMedian = median.process(imgReflection);        
        //ImageWrite("src/image/Median.png", imgMedian);
        
        /*------------------ Gausian 2D ------------------------*/
        Gaussian2D g2d = new Gaussian2D();
        float[][] kernel = g2d.kernel(1.6f, 15, false);
        int[][] imgGaussian = g2d.convolve(imgMedian, kernel);        
        ImageWrite("src/image/Gaussian.png", imgGaussian);
                
        /*---------------- Sobel Edge Detection -----------------*/
        Sobel sobel = new Sobel();
        sobel.process("src/image/Gaussian.png");
        ImageWrite( "src/image/SobelMagnitute.png", sobel.Magnitute);
        ImageWrite( "src/image/SobelDirection.png", sobel.Direction);
        
        
        /*---------- Scaledown Remove Reflection Padding -------*/
        //int[][] simage = ref.ScaleDown(oimage,padding_x,padding_y);
        //ImageWrite(simage, "src/image/scaledown.png");       
    }
    /*--------------------------------------------------------------------------------------------*/    
    private static int[][] ProcessPrecentile() throws IOException {
        
        Percentile percentile = new Percentile();
        int [] mapHist = percentile.readHistogram(sInput);      
        int[] cutoff = percentile.setCutoff(mapHist, 10.0); 
       
        int[][] green = ImageRead(sInput);
        
        percentile.WriteStretchedImage(green
                                        , percentile.first
                                        , percentile.last
                                        , new int[0]
                                        , "src/image/zCutOff.png");
        
        int[] stretchedHist = percentile.stretchMap(percentile.first, percentile.last);
        
        percentile.WriteStretchedImage(green
                                        , percentile.first
                                        , percentile.last
                                        , stretchedHist
                                        , "src/image/Percentile.png");        
        return green;
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
    private static void ImageWrite(String filename, double img[][]) throws IOException {

            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = (int)img[i][j];
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
    }    
    /*--------------------------------------------------------------------------------------------*/    
}
