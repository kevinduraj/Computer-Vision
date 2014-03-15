package project02;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

public class Histogram {

    private Map<Integer, Integer> mapSource = new TreeMap();
    private Map<Integer, Integer> mapFinal = new TreeMap();
    
    private Integer totalPixels;
    private Integer cutoff;
    private Integer cutoffHigh;


    /*--------------------------------------------------------------------------------------------*/
    public Histogram() {
        totalPixels = 0;
        cutoff = 0;
        cutoffHigh = 0;
    }
    /*--------------------------------------------------------------------------------------------*/

    public void readHistogram(String filename) {

        try {

            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            int grn[][] = new int[bi.getHeight()][bi.getWidth()];

            for (int i = 0; i < grn.length; ++i) {
                for (int j = 0; j < grn[i].length; ++j) {
                    
                    totalPixels ++;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    incrementSource(grn[i][j], 1);                   
                }
            }

        } catch (IOException e) {
            System.out.println(e + "image I/O error");
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public void incrementSource(Integer key, Integer value) {

        int count = mapSource.containsKey(key) ? mapSource.get(key) : 0;
        count += value;
        mapSource.put(key, count);
    }
    /*--------------------------------------------------------------------------------------------*/

    public void incrementFinal(Integer key, Integer value) {

        int count = mapFinal.containsKey(key) ? mapFinal.get(key) : 0;
        count += value;
        mapFinal.put(key, count);

    }   

    /*--------------------------------------------------------------------------------------------*/
    public void displayHistogram() {

        for (Map.Entry<Integer, Integer> entry : mapSource.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }
    /*--------------------------------------------------------------------------------------------*/
    public void displayCutoff() {

        for (Map.Entry<Integer, Integer> entry : mapFinal.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }    
    /*--------------------------------------------------------------------------------------------*/
    public void cutoffHistogram() {

        int sum = 0;
        
        for (Map.Entry<Integer, Integer> entry : mapSource.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            
            sum += value;
            if(sum < cutoff) mapFinal.put(key, 0);
            else if(sum > cutoffHigh) mapFinal.put(key, 0);
            else mapFinal.put(key, value);
        }
        
        
    }    
    /*--------------------------------------------------------------------------------------------*/
    public void setCutoff(int percent) {

        cutoff = (int) (totalPixels * 0.1);   
        cutoffHigh = totalPixels - cutoff;
    }
    /*--------------------------------------------------------------------------------------------*/
    public Integer getCutoff() {
        if (cutoff == 0)
            cutoff = (int) (totalPixels * 0.1);   
        return cutoff;
    }    
    /*--------------------------------------------------------------------------------------------*/
    public void displayAll() {

        System.out.println("\n-------------------------------------\n");
        System.out.println("Total Pixels: " + totalPixels);
        System.out.println("Cutoff      : " + cutoff);
        System.out.println("CutoffHigh  : " + cutoffHigh);
        
          
    }    
    /*--------------------------------------------------------------------------------------------*/    
}
