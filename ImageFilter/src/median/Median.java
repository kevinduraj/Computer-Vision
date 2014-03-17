package median;

import imagefilter.ImageReadWrite;

public class Median {

    int nrows, ncols;
    int in_img[][], out_img[][];

    public void process() {
        
        nrows = 512;
        ncols = 512;
        
        ImageReadWrite io = new ImageReadWrite();
        in_img = io.ImageRead("src/image/Lenna.png");
        out_img = new int[nrows][ncols];

        // Median filtering
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                out_img[i][j] = median(i, j);
            }
        }

        
        io.ImageWrite("src/image/Lenna4.png", out_img);                
    }

    public int median(int i, int j) {
        int m, n, count, t[], tmp;

        t = new int[9];
        for (m = i - 1, count = 0; m <= i + 1; m++) {
            for (n = j - 1; n <= j + 1; n++) {
                if (m >= 0 && m < nrows && n >= 0 && n < ncols) {
                    t[count++] = in_img[m][n];
                }
            }
        }

        //--- Bubble Sort ---//
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

} // end class MedianFilter
