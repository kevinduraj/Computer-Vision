package median;

import imagefilter.ImageReadWrite;

public class Outlier {

    int nrows, ncols;
    int image1[][], image2[][];
    int totalOutliers = 0;

    public void process(String fileIn, String fileOut) {

        ImageReadWrite io = new ImageReadWrite();
        image1 = io.ImageRead(fileIn);

        nrows = image1.length;
        ncols = image1[1].length;

        image2 = new int[nrows][ncols];

        //--- Browse Source Image Matrix----//
        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                int outlier = median(i, j);

                if (outlier == 0) {
                    image2[i][j] = image1[i][j];
                }
            }
        }
        io.ImageWrite(fileOut, image2);
        System.out.println("---------------------------------");
        System.out.println("Total Outliers: " + totalOutliers);
    }

    public int median(int i, int j) {
        int m, n, count, kernel[], tmp;

        //--- Browse Kernel Matrix ---//
        kernel = new int[9];
        for (m = i - 1, count = 0; m <= i + 1; m++) {
            for (n = j - 1; n <= j + 1; n++) {
                if (m >= 0 && m < nrows && n >= 0 && n < ncols) {
                    kernel[count++] = image1[m][n];
                }
            }
        }

        //--- Apply Bubble Sort ---//
        for (m = 0; m < count - 1; m++) {
            for (n = m + 1; n < count; n++) {
                if (kernel[m] < kernel[n]) {
                    tmp = kernel[m];
                    kernel[m] = kernel[n];
                    kernel[n] = tmp;
                }
            }
        }

        int average = kernel[count / 2];

        if ((image1[i][j] - average) > 50) {

            totalOutliers++;
            System.out.format("0=%3d ", kernel[0]);
            System.out.format("1=%3d ", kernel[1]);
            System.out.format("2=%3d ", kernel[2]);
            System.out.format("3=%3d ", kernel[3]);
            System.out.format("4=%3d ", kernel[4]);
            System.out.format("5=%3d ", kernel[5]);
            System.out.format("6=%3d ", kernel[6]);
            System.out.format("7=%3d ", kernel[7]);
            System.out.format("8=%3d ", kernel[8]);
            System.out.println();
            return kernel[count / 2];
        } else {
            return 0;
        }
    }
}
