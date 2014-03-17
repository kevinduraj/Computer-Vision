package preprocessing;

import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.IOException;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;

public class Main {

    public static final int DEBUG = 1;
    
    /*--------------------------------------------------------------------------------------------
                                       M A I N
     --------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        //CutOffImage1();
        //Binarization2();        
        //OtsuBinarization3();    
        
    }
    
    /*--------------------------------------------------------------------------------------------
        https://github.com/Ela88/ImageProcessing/blob/master/code%20raf/ImageProcessingPipe/fhv/pipes_and_filters/utils/ImageFilters.java
    ---------------------------------------------------------------------------------------------*/
    public static PlanarImage medianFilter(PlanarImage image) {
        
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(image);
        pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
        pb.add(3);

        RenderedImage dst = JAI.create("MedianFilter", pb);
        PlanarImage finalImg = (PlanarImage) dst;
        return finalImg;
    }
        
    /*---------------------------------------------------------------------------------------------
    Program 1.)
        Perform a histogram stretch operation 
        using the 10th and 90th percentile bins as cutoff points
    ----------------------------------------------------------------------------------------------*/
    private static void CutOffImage1() {
        
        Histogram hist = new Histogram();
        int [] mapHist = hist.readHistogram("src/image/Lenna.png");
        if(DEBUG==2) display(mapHist);
        
        int[] cutoff = hist.setCutoff(mapHist, 10.0); 
        if(DEBUG==2) display(cutoff);       
        if(DEBUG==1) hist.displayAll();
       
        ImageReadWrite image = new ImageReadWrite();
        int grn[][] = image.ImageRead("src/image/Lenna.png");
        image.WriteStretchedImage(grn, hist.first, hist.last, new int[0], "src/image/LennaCutoff.png");
        
        int[] stretchedHist = hist.stretchMap(hist.first, hist.last);
        if(DEBUG==2) display(stretchedHist);
        
        image.WriteStretchedImage(grn, hist.first, hist.last, stretchedHist, "src/image/LennaStretch.png");     
        
    }
    /*----------------------------------------------------------------------------------------------
      Program 2.)
        Perform a binarization operation using a threshold value of 128
      --------------------------------------------------------------------------------------------*/
    private static void Binarization2() {

        /*------------------- Histogram -------------------------*/
        Statistics stat = new Statistics("src/image/Lenna.png");
        int mean = (int) stat.getMean();        
        System.out.println("My Mean = " + mean);
        
        ImageReadWrite image = new ImageReadWrite();
        int grn[][] = image.ImageRead("src/image/Lenna.png");
        
        Binarization bin = new Binarization();
        bin.binarize(grn, mean, "src/image/LennaBin"+mean+".png");
    }    
    /*----------------------------------------------------------------------------------------------
      Program 3.)
        Perform a binarization operation on the green component 
        using the Otsu optimal threshold algorithm to compute the threshold
      --------------------------------------------------------------------------------------------*/
    
    private static void OtsuBinarization3() throws IOException {
        
        OtsuBinarize otsu = new OtsuBinarize("src/image/Lenna.png");
        String filename = otsu.run();
        if(DEBUG==2) System.out.println("Otsu output" + filename);
    }

    
    /*---------------------------------------------------------------------------------------------
                             Display Integer Histogram Array
    ----------------------------------------------------------------------------------------------*/
    private static void display(int[] map) {

        System.out.println("\n--------------------------------\n");
        for (int key=0; key<map.length; key++) {
            int value = map[key];
            System.out.format("%3d -> %5d\n", key, value);
        }
    } 
    /*--------------------------------------------------------------------------------------------*/    
}
