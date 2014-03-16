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

    private Integer first;
    private Integer last;
    private Integer points;


    /*--------------------------------------------------------------------------------------------*/
    public Histogram() {
        totalPixels = 0;
        cutoff = 0;
        cutoffHigh = 0;
        first = 0;
        last = 0;
        points = 0;
    }
    /*--------------------------------------------------------------------------------------------*/

    public void readHistogram(String filename) {

        try {

            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            int grn[][] = new int[bi.getHeight()][bi.getWidth()];

            for (int i = 0; i < grn.length; ++i) {
                for (int j = 0; j < grn[i].length; ++j) {

                    totalPixels++;
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
                first = key+1;             //---------- set first ----------//
                
            } else if (sum > cutoffHigh) {
                
                if(last==0) last = key-1;  //---------- set last  ----------//
                mapCutoff.put(key, 255);
                
            } else {
                mapCutoff.put(key, value);
            }
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public void setStretch() {
        
        double increment = 255 / (last-first);    // 1.8613        
        double counter=first;                     // 51
        
        //mapStretch.put(0, first);
        
        for (int i=first; i<=last; i++) { 
            
            double res = (i - first) * 1.8613;
            //counter = counter + increment;
            mapStretch.put((int)res, i);           
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

    public int lookup(int key) {
        return mapCutoff.containsKey(key) ? mapCutoff.get(key) : 0;
    }
    /*--------------------------------------------------------------------------------------------*/

    public void displayAll() {

        System.out.println("\n------------------------------------");
        System.out.println("Total Pixels : " + totalPixels);
        System.out.println("Cutoff       : " + cutoff);
        System.out.println("CutoffHigh   : " + cutoffHigh);
        System.out.println("Lookup 187   : " + lookup(187));
        System.out.println("-------------------------------------");
        System.out.println("First        : " + first);
        System.out.println("Last         : " + last);
        System.out.println("Points       : " + points);        
        System.out.println("-------------------------------------");

    }    
    /*--------------------------------------------------------------------------------------------*/
    public int[][] ImageRead(String filename) {

        try {

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

        } catch (IOException e) {
            System.out.println("image I/O error");
            return null;
        }
    }   
    /*--------------------------------------------------------------------------------------------*/
    public void ImageWrite(int img[][], String filename) {

        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    
                    int val = img[i][j];               
                    if(lookup(val) == 0) val=0;                       
                    
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            //--- Write output image
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }    
    /*--------------------------------------------------------------------------------------------*/       
}
