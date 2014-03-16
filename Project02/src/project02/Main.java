package project02;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("Project 02 - Histogram");
        MakeCutoffImage();
        
        //Histogram hist2 = new Histogram();
        //hist2.readHistogram("src/image2/LennaCutoff.png");
        //hist2.displayHistogram();
             
    }

    private static void MakeCutoffImage() {
        /*---------------- Loading Data -------------------------*/
        Histogram hist = new Histogram();
        hist.readHistogram("src/image2/Lenna.png");
        Map<Integer, Integer> cutoff = hist.setCutoff(10.0); 
        
        displayMap(cutoff);
        hist.displayAll();
        
        /*-------------------------------------------------------*/
        //hist.setStretch();
        //hist.displayStretch();
        /*-------------------------------------------------------*/
        //int grn[][] = hist.ImageRead("src/image2/Lenna.png");
        //hist.ImageWrite(grn, "src/image2/LennaCutoff.png");
    }
    
    /*--------------------------------------------------------------------------------------------*/
    private static void displayMap(Map<Integer, Integer> map) {

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }
    /*--------------------------------------------------------------------------------------------*/    
}
