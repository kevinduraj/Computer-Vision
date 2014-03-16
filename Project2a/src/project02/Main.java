package project02;

import java.util.Map;

public class Main {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) {

        System.out.println("Project 02 - Histogram");
        //MakeCutoffImage();
        MakeStretchImage();        
             
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void MakeStretchImage() {
        Histogram hist = new Histogram();
        int[] mapHist = hist.readHistogram("src/image2/Lenna.png");
        display(mapHist);
        
        int[] mapStretched = hist.stretchMap(51, 188);
        display(mapStretched);
        
        ImageReadWrite image = new ImageReadWrite();
        int grn[][] = image.ImageRead("src/image2/Lenna.png"); 
        image.ImageWriteStretch(grn, 51, 188, mapStretched, "src/image2/LennaStretch.png");
    }
    /*--------------------------------------------------------------------------------------------*/
    private static void MakeCutoffImage() {
        
        Histogram hist = new Histogram();
        int [] mapHist = hist.readHistogram("src/image2/Lenna.png");
        //display(mapHist);
        
        int[] cutoff = hist.setCutoff(mapHist, 10.0); 
        display(cutoff);
        
        //hist.displayAll();
       
        ImageReadWrite image = new ImageReadWrite();
        int grn[][] = image.ImageRead("src/image2/Lenna.png");
        image.ImageWriteHistogram(grn, hist.first, hist.last, "src/image2/LennaCutoff.png");
        
    }
    /*--------------------------------------------------------------------------------------------*/
    private static void display(int[] map) {

        System.out.println("\n--------------------------------\n");
        for (int key=0; key<map.length; key++) {
            int value = map[key];
            System.out.format("%3d -> %5d\n", key, value);
        }
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
