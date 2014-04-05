package regionprocessing;

public class DurajChainCode {
    
    private int pixel = 255;
    public int[][]image;
    
    /*--------------------------------------------------------------------------------------------*/
    public DurajChainCode(int[][]image) {        

        this.image = image;        
    }
    
    /*--------------------------------------------------------------------------------------------*/
    public void process( int pixelObject, int pixelChain) {        
        
        this.pixel = pixelChain;        
        int height=image.length;
        int width=image[0].length;
        
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
}
