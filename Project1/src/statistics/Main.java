/*--------------------------------------------------------------------------------------------------
1. Read an image file (src/image/Week_01_Homework.png) 
2. Separate the image into three components (red-green-blue) 
3. Compute the mean, standard deviation, minimum, and maximum of the pixels in the green component 
4. Subtract the mean from each pixel of the input green component 
a. If the pixel value drops below 0, set that pixel value to 0 
5. Write the resultant image to an image file (BMP, PNG) 
6. Subtract the standard deviation from each pixel of the input green component 
a. If the pixel value drops below 0, set that pixel value to 0 
7. Write the resultant image to an image file (BMP, PNG, other “standard” uncompressed format) 

Written by: Kevin T. Duraj
Reference:
ImageJ: http://rsbweb.nih.gov/ij/developer/source/index.html
--------------------------------------------------------------------------------------------------*/
package statistics;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Project 01");
        
        /*---------------- Loading Data -------------------------*/
        List<Double> data = new ArrayList<>();
        ImageRW image = new ImageRW();
        int grn[][] = image.readGreen("src/image/Week_01_Homework.png", data);
        
        
        /*------------------- Histogram -------------------------*/
        Statistics stat = new Statistics(data);
        System.out.format("\nMean     : %.3f", stat.getMean());
        System.out.format("\nVariance : %.3f", stat.getVariance());
        System.out.format("\nStdDev   : %.3f", stat.getStdDev());
               
        System.out.println("\n------------------------");
        System.out.format("Max      : %3d", stat.getMax());
        System.out.format("\nMin      : %3d\n", stat.getMin()); 
        System.out.println("------------------------");

        /*-------------------------------------------------------*/
        
        image.substract(grn, stat.getMean(), "src/image/SubstractMean.png");
        image.substract(grn, stat.getStdDev(), "src/image/SubstractStdDev.png");
    
    }
}
