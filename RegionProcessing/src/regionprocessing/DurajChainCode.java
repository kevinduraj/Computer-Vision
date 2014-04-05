package regionprocessing;

import java.util.ArrayList;
import java.util.List;

public class DurajChainCode {
    
    private int pixel;
    public int[][]image;
    private int height;
    private int width;
    public List<String> list;
    
    /*--------------------------------------------------------------------------------------------*/
    public DurajChainCode(int[][]image) {        

        this.image = image;        
        this.height=image.length;
        this.width=image[0].length;
        this.list = new ArrayList<>();
        
    }
    
    /*--------------------------------------------------------------------------------------------*/
    public void process( int pixelObject, int pixelChain) {        
        
        this.pixel = pixelChain;        
        
        /*---- Left to Right ----*/
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if ((image[j][i] == pixelObject) && (image[j][i + 1] != pixelObject)) {
                    image[j][i + 1] = pixelChain;
                }
            }
        }

        /*---- Right to Left ----*/
        for (int j = 0; j < height; j++) {
            for (int i = width - 1; i >= 0; i--) {
                if ((image[j][i] == pixelObject) && (image[j][i - 1] != pixelObject)) {
                    image[j][i - 1] = pixelChain;
                }
            }
        }
        /*---- Top to Bottom ----*/
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height-1; j++) {
                if ((image[j+1][i] == pixelObject) && (image[j+2][i] != pixelObject)) {
                    image[j+2][i] = pixelChain;
                }
            }
        }
        /*---- Bottom to Top ----*/
        for (int i = 0; i < width; i++) {
            for (int j = height-1; j>=0; j--) {
                if ((image[j][i] == pixelObject) && (image[j-1][i] != pixelObject)) {
                    image[j-1][i] = pixelChain;
                }
            }
        }         
    }
    /*--------------------------------------------------------------------------------------------*/
    public int[] findFirst(int chain) {
                
        int[] first = new int[2];
        
        /*---- Left to Right ----*/
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (image[j][i] == chain)  {
                    first[0] = j;
                    first[1] = i;
                    return first;
                }
            }
        }
        return null;
    }
    /*--------------------------------------------------------------------------------------------*/    
    public int[] getChain(int[] pix) {
        
        int h = pix[0];        
        int w = pix[1];                 
        int[]n = new int[2];
        
        image[h][w] = 200;
             
        
        // East
        if(image[h][w+1] == 255) {
            n[0] = h;
            n[1] = w+1;
            list.add("E");
        }
        // North East
        else if(image[h-1][w+1] == 255) {
            //System.out.println( "\th=" + (h-1) + "\tw=" + (w+1));
            n[0] = h-1;
            n[1] = w+1;
            list.add("NE");
        }            
        // North
        else if(image[h-1][w] == 255) {
            n[0] = h-1;
            n[1] = w;
            list.add("N");            
        }        
        // North West
        else if(image[h-1][w-1] == 255) {
            n[0] = h-1;
            n[1] = w-1;
            list.add("NW");
        }
        // West
        else if(image[h][w-1] == 255) {
            n[0] = h;
            n[1] = w-1;
            list.add("W");                                    
        }
        // South West
        else if(image[h+1][w-1] == 255) {
            n[0] = h+1;
            n[1] = w-1;
            list.add("SW");                        
        }
        // South
        else if(image[h+1][w] == 255) {
            n[0] = h+1;
            n[1] = w;
            list.add("S");            
        }                
        // South East
        else if(image[h+1][w+1] == 255) {
            n[0] = h+1;
            n[1] = w+1;
            list.add("SE");
        }
                
        return n;
            
    }
    /*--------------------------------------------------------------------------------------------*/        
}
