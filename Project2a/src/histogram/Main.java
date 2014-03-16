package histogram;

public class Main {

    public static final int DEBUG = 1;
    
    /*--------------------------------------------------------------------------------------------
                                       M A I N
     --------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {

        System.out.println("Project 02 - Histogram");
        
        CutOffImage();
        Binarization();
             
    }

    /*---------------------------------------------------------------------------------------------
     Perform a histogram stretch operation using the 10th and 90th percentile bins as cutoff points
    ----------------------------------------------------------------------------------------------*/
    private static void CutOffImage() {
        
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
                   Perform a binarization operation using a threshold value of 128
      --------------------------------------------------------------------------------------------*/
    private static void Binarization() {

        ImageReadWrite image = new ImageReadWrite();
        int grn[][] = image.ImageRead("src/image/Lenna.png");
        
        Binarization bin = new Binarization();
        bin.binarize(grn, 100, "src/image/LennaBin100.png");
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
