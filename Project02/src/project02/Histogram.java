package project02;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

public class Histogram {

    Map<Integer, Integer> map = new TreeMap();
    
    /*--------------------------------------------------------------------------------------------*/
    public void readHistogram(String filename) {

        try {

            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            int grn[][] = new int[bi.getHeight()][bi.getWidth()];

            for (int i = 0; i < grn.length; ++i) {
                for (int j = 0; j < grn[i].length; ++j) {
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    incrementValue(grn[i][j], 1);
                }
            }

        } catch (IOException e) {
            System.out.println(e + "image I/O error");
        }
    }
    /*--------------------------------------------------------------------------------------------*/
    public void incrementValue(Integer key, Integer value) {

        int count = map.containsKey(key) ? map.get(key) : 0;
        count += value;
        map.put(key, count);

    }

    /*--------------------------------------------------------------------------------------------*/
    public void displayHistogram() {

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }    
    /*--------------------------------------------------------------------------------------------*/
}
