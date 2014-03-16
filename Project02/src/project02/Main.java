package project02;

import java.util.Map;

public class Main {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {

        System.out.println("Project 02 - Histogram");
        //MakeCutoffImage();
        
        Histogram hist = new Histogram();
        //Map<Integer, Integer> mapHist = hist.readHistogram("src/image2/LennaCutoff.png");
        //displayMap(mapHist);
        
        Map<Integer, Integer> mapStretched = hist.stretchMap(51, 188);
        displayMap(mapStretched);
             
    }
    /*--------------------------------------------------------------------------------------------*/
    
    private static void MakeCutoffImage() {
        
        Histogram hist = new Histogram();
        Map<Integer, Integer> mapHist = hist.readHistogram("src/image2/Lenna.png");
        displayMap(mapHist);
        
        Map<Integer, Integer> cutoff = hist.setCutoff(10.0); 
        displayMap(cutoff);
        
        hist.displayAll();
       
        ImageReadWrite image = new ImageReadWrite();
        int grn[][] = image.ImageRead("src/image2/Lenna.png");
        image.ImageWriteHistogram(grn, hist.first, hist.last, "src/image2/LennaCutoff.png");
        
    }
    
    /*--------------------------------------------------------------------------------------------*/
    private static void displayMap(Map<Integer, Integer> map) {

        System.out.println("\n--------------------------------\n");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.format("%3d -> %5d\n", key, value);
        }
    }
    /*--------------------------------------------------------------------------------------------*/    
}
