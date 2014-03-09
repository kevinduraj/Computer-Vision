package project01;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*------------------------------------------------------------------------------------------------*/
public class ImageProcess {
    
    TreeMap<Integer, Integer> map = new TreeMap<>();
    Integer totalPixels = 0;
    Integer totalSum = 0;

    
    /*--------------------------------------------------------------------------------------------*/
    public void loadData(int img[][], List<Double> data) {
        
        try {
            
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {

                    double value = img[i][j];
                    data.add(value);
                    
                    //totalPixels++;
                    //totalSum += val;
                    //incrementValue(val, 1); 
                    
                    
                }
            }
     
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/
    public void incrementValue(Integer key, Integer value) {

        int count = map.containsKey(key) ? map.get(key) : 0;
        count += value;
        map.put(key, count);

    }
    /*--------------------------------------------------------------------------------------------*/
    public void displayTreeMap() {
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            
            Integer key = entry.getKey();
            Integer value = entry.getValue();
           
            System.out.println(key + " => " + value);
        }
        
    }  
    /*--------------------------------------------------------------------------------------------*/
    public void displayHistogram() {
        
        float mean = (float) totalSum / totalPixels;
        System.out.println("Total Pixels : " + totalPixels);
        System.out.println("Total Sum    : " + totalSum);
        System.out.format("Mean         : %.3f\n", mean);
        
    }
    /*--------------------------------------------------------------------------------------------*/
}
/*------------------------------------------------------------------------------------------------*/
