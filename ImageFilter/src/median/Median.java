package median;

import imagefilter.ImageReadWrite;

public class Median {

    int nrows, ncols;
    int image1[][], image2[][];

    public void process(String fileIn, String fileOut) {
        
        ImageReadWrite io = new ImageReadWrite();
        image1 = io.ImageRead(fileIn);
        
        nrows = image1.length; 
        ncols = image1[1].length;
               
        image2 = new int[nrows][ncols];

        //--- Browse Source Image Matrix----//
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                image2[i][j] = median(i, j);
            }
        }
        
        io.ImageWrite(fileOut, image2);                
    }

    public int median(int i, int j) {
        int m, n, count, t[], tmp;

        //--- Browse Kernel Matrix ---//
        t = new int[9];
        for (m = i - 1, count = 0; m <= i + 1; m++) {
            for (n = j - 1; n <= j + 1; n++) {
                if (m >= 0 && m < nrows && n >= 0 && n < ncols) {
                    t[count++] = image1[m][n];
                }
            }
        }

        //--- Apply Bubble Sort ---//
        for (m = 0; m < count - 1; m++) {
            for (n = m + 1; n < count; n++) {
                if (t[m] < t[n]) {
                    tmp = t[m];
                    t[m] = t[n];
                    t[n] = tmp;
                }
            }
        }
        return t[count / 2];
    }
} 
