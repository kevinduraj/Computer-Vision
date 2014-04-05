package regionprocessing;

public class DurajChainCode {
    
    private int pixel = 255;
    
    public DurajChainCode(int pixel) {
        this.pixel = pixel;
    }
    
    public void process(int[][]array) {
        
        int height=array.length;
        int width=array[0].length;
        
        /*---- Left to Right ----*/
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if ((array[j][i] == 1) && (array[j][i + 1] != 1)) {
                    array[j][i + 1] = pixel;
                }
            }
        }

        /*---- Right to Left ----*/
        for (int j = 0; j < height; j++) {
            for (int i = width - 1; i >= 0; i--) {
                if ((array[j][i] == 1) && (array[j][i - 1] != 1)) {
                    array[j][i - 1] = pixel;
                }
            }
        }
        /*---- Top to Bottom ----*/
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height-1; j++) {
                if ((array[j+1][i] == 1) && (array[j+2][i] != 1)) {
                    array[j+2][i] = pixel;
                }
            }
        }
        /*---- Bottom to Top ----*/
        for (int i = 0; i < width; i++) {
            for (int j = height-1; j>=0; j--) {
                if ((array[j][i] == 1) && (array[j-1][i] != 1)) {
                    array[j-1][i] = pixel;
                }
            }
        }         
    }
}
