package filter;

import java.io.*;

public class Median {
  static int nrows, ncols;
  static int in_img[][], out_img[][];

  public static void main(String[] args) {
    if (args.length != 4) {
      System.out.println("Usage: java Median <nrows> <ncols> <in_img> <out_img>");
      System.exit(0);
    }
    nrows = Integer.parseInt(args[0]);
    ncols = Integer.parseInt(args[1]);
    in_img = new int[nrows][ncols];
    ArrayIO.readByteArray(args[2], in_img, nrows, ncols);
    out_img = new int[nrows][ncols];

    // Median filtering
    for (int i=0; i<nrows; i++)
      for (int j=0; j<ncols; j++)
        out_img[i][j] = median(i, j);

    ArrayIO.writeByteArray(args[3], out_img, nrows, ncols);
  }

  public static int median(int i, int j) {
    int  m, n, count, t[], tmp;

    t = new int[9];
    for (m=i-1, count=0; m<=i+1; m++)
      for (n=j-1; n<=j+1; n++)
        if (m>=0 && m<nrows && n>=0 && n<ncols)
          t[count++] = in_img[m][n];

    // Bubble sort
    for (m=0; m<count-1; m++)
      for (n=m+1; n<count; n++)
        if (t[m] < t[n]) {
          tmp = t[m];
          t[m] = t[n];
          t[n] = tmp;
        }
    return t[count/2];
  }

} // end class MedianFilter