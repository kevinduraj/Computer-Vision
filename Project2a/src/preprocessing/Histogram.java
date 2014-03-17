package preprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

public class Histogram {

    private Map<Integer, Integer> mapSource = new TreeMap();
    private Map<Integer, Integer> mapCutoff = new TreeMap();

    private Integer totalPixels;
    private Integer cutoff;
    private Integer cutoffHigh;

    Integer first;
    Integer last;


    /*--------------------------------------------------------------------------------------------*/
    public Histogram() {
        totalPixels = 0;
        cutoff = 0;
        cutoffHigh = 0;
        first = 0;
        last = 0;
    }
    /*--------------------------------------------------------------------------------------------*/

    //public Map<Integer, Integer> readHistogram(String filename) {
    public int[] readHistogram(String filename) {

        int pixel[] = new int[256];
        
        try {

            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            int grn[][] = new int[bi.getHeight()][bi.getWidth()];

            for (int i = 0; i < grn.length; ++i) {
                for (int j = 0; j < grn[i].length; ++j) {

                    totalPixels++;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    int key = grn[i][j];
                    
                    //--- increment mapSource ---//
                    //int count = mapSource.containsKey(key) ? mapSource.get(key) : 0;
                    //count += 1;
                    //mapSource.put(key, count);
                    
                    //--- increment pixel ---//
                  
                    pixel[key] += 1;
                   
                }
            }

        } catch (IOException e) {
            System.out.println(e + "image I/O error");
        }

        //return mapSource;
        return pixel;

    }
    /*--------------------------------------------------------------------------------------------*/

    public void incrementFinal(Integer key, Integer value) {

        int count = mapCutoff.containsKey(key) ? mapCutoff.get(key) : 0;
        count += value;
        mapCutoff.put(key, count);

    }
    /*--------------------------------------------------------------------------------------------*/

    public int[] stretchMap(int first, int last) {

        int[] mapStretch = new int[256];
        
        double increment = (double) 253 / (last - first);    // 1.8540 = 254 / (188-51)     
        System.out.format("--- Increment = %.4f ---\n", increment);
        
        for (int oldKey = first; oldKey <= last; oldKey++) {

            double newKey = ((oldKey - first) * increment) + 1 ;   // 16.75 = (60-51) * 1.8613
            mapStretch[oldKey] = (int) newKey;
        }
        return mapStretch;
    }

    /*----------------------------------------------------------------------------------------------
     Cutoff Histogram 
     set 10% to   0 close to 0 
     set 10% to 255 close to 255
     ----------------------------------------------------------------------------------------------*/
    public int[] setCutoff(int[] source, double percent) {

        percent = percent / 100;
        cutoff = (int) (totalPixels * percent);
        cutoffHigh = totalPixels - cutoff;
     
        int sum = 0;
        int[] pixel = new int[256];

        for(int key=0; key<source.length; key++) {
            
            int value = source[key];

            sum += value;
            if (sum < cutoff) {

                pixel[key] = 0;
                first = key + 1;             //---------- set first ----------//

            } else if (sum > cutoffHigh) {

                if (last == 0) {
                    last = key - 1;          //---------- set last  ----------//
                }
                pixel[key] = 255;

            } else {
                pixel[key] = value;
            }
        }
        
        
        return pixel;

    }
    /*--------------------------------------------------------------------------------------------*/

    public void displayAll() {

        System.out.println("\n------------------------------------");
        System.out.println("Total Pixels : " + totalPixels);
        System.out.println("Cutoff       : " + cutoff);
        System.out.println("CutoffHigh   : " + cutoffHigh);
        System.out.println("-------------------------------------");
        System.out.println("First        : " + first);
        System.out.println("Last         : " + last);
        System.out.println("-------------------------------------");

    }   
}
