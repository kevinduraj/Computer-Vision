package project02;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

public class Histogram {

    private Map<Integer, Integer> mapSource = new TreeMap();
    private Map<Integer, Integer> mapCutoff = new TreeMap();
    private Map<Integer, Integer> mapStretch = new TreeMap();

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

    public Map<Integer, Integer> readHistogram(String filename) {

        try {

            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            int grn[][] = new int[bi.getHeight()][bi.getWidth()];

            for (int i = 0; i < grn.length; ++i) {
                for (int j = 0; j < grn[i].length; ++j) {

                    totalPixels++;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;

                    // incrementSource(grn[i][j], 1);
                    int key = grn[i][j];
                    int count = mapSource.containsKey(key) ? mapSource.get(key) : 0;
                    count += 1;
                    mapSource.put(key, count);

                }
            }

        } catch (IOException e) {
            System.out.println(e + "image I/O error");
        }

        return mapSource;

    }
    /*--------------------------------------------------------------------------------------------*/

    public void incrementSource(Integer key, Integer value) {

        int count = mapSource.containsKey(key) ? mapSource.get(key) : 0;
        count += value;
        mapSource.put(key, count);
    }
    /*--------------------------------------------------------------------------------------------*/

    public void incrementFinal(Integer key, Integer value) {

        int count = mapCutoff.containsKey(key) ? mapCutoff.get(key) : 0;
        count += value;
        mapCutoff.put(key, count);

    }

    /*----------------------------------------------------------------------------------------------
     Cutoff Histogram 
     set 10% to   0 close to 0 
     set 10% to 255 close to 255
     ----------------------------------------------------------------------------------------------*/
    private void cutoffHistogram() {

        int sum = 0;

        for (Map.Entry<Integer, Integer> entry : mapSource.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            sum += value;
            if (sum < cutoff) {

                mapCutoff.put(key, 0);
                first = key + 1;             //---------- set first ----------//

            } else if (sum > cutoffHigh) {

                if (last == 0) {
                    last = key - 1;  //---------- set last  ----------//
                }
                mapCutoff.put(key, 255);

            } else {
                mapCutoff.put(key, value);
            }
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public void setStretch() {

        double increment = 255 / (last - first);    // 1.8613        
        double counter = first;                     // 51

        //mapStretch.put(0, first);
        for (int i = first; i <= last; i++) {

            double res = (i - first) * 1.8613;
            //counter = counter + increment;
            mapStretch.put((int) res, i);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public Map<Integer, Integer> setCutoff(double percent) {

        percent = percent / 100;
        cutoff = (int) (totalPixels * percent);
        cutoffHigh = totalPixels - cutoff;
        cutoffHistogram();
        return mapCutoff;

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
/*
   value = mapCutoff.containsKey(key) ? mapCutoff.get(key) : 0;
*/