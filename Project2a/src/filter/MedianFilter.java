package filter;

public class MedianFilter {

    static int nrows, ncols;
    static int in_img[][], out_img[][];

    public static int[][] process(int[][] in_img) {        
        
        //in_img = ArrayIO.ImageRead("src/image/Lenna.png");
        
        nrows = 512;
        ncols = 512;
        
        out_img = new int[nrows][ncols];

        // Median filtering
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                out_img[i][j] = median(i, j);
            }
        }

        //ArrayIO.ImageWrite("src/image/Lenna4.png", out_img);                
        return out_img;
    }

    public static int median(int i, int j) {
        int m, n, count, t[], tmp;

        //--- travel the kernel ----//
        t = new int[9];
        for (m = i - 1, count = 0; m <= i + 1; m++) {
            for (n = j - 1; n <= j + 1; n++) {
                if (m >= 0 && m < nrows && n >= 0 && n < ncols) {
                    t[count++] = in_img[m][n];
                }
            }
        }

        //--- bubble sort ---//
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
