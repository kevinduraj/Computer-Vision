package reflectionpadding;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Rotate {

    private String input;
    static BufferedImage image;

    /*--------------------------------------------------------------------------------------------*/
    public Rotate(String input) throws IOException {
        this.input = input;

        FlipVerticaly();
        FlipHorizontaly();
        Rotate180();
    }

    /*--------------------------------------------------------------------------------------------*/
    public void displayBorder(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                // horizontal
                if (i == 0) {
                    System.out.format("%3d ", img[i][j]);
                } // vertical;
                else if ((i != 0) && (j == 0)) {
                    System.out.format("%3d ", img[i][j]);
                } else if (i == img.length - 1) {
                    System.out.format("%3d ", img[i][j]);
                } else if ((i > 0) && (j == img.length - 1)) {
                    System.out.format("%3d ", img[i][j]);
                } else {
                    System.out.format("%3s ", "x");
                }
            }

            System.out.println();

        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] reflection(int[][] padded, int orig[][], int vert[][], int horiz[][], int rotate[][], int size) {

        int rows = orig.length;
        int cols = orig[0].length;

        int nrow = padded.length;
        int ncol = padded[0].length;

        //--- Left Side ---//       
        for (int i = size; i < rows + size; i++) {
            for (int c = 1, j = size - 1; j >= 0; c++, j--) {
                padded[i][j] = horiz[i - size][cols - c];
            }
        }

        //--- Right Side ---//
        for (int i = size; i < rows + size; i++) {
            for (int c = 0, j = ncol - size; j < ncol; c++, j++) {
                padded[i][j] = horiz[i - size][c];
            }
        }

        //--- Top Side  ----//
        for (int i = 0; i < size; i++) {
            for (int j = size; j < cols + size; j++) {
                padded[i][j] = vert[(cols - size) + i][j - size];
            }
        }

        //--- Bottom Side ---//
        for (int i = nrow - size; i < nrow; i++) { // vertical
            for (int j = 0; j < cols; j++) { // horizontal
                padded[i][size + j] = vert[i - (nrow - size)][j];
            }
        }

        //--- Top Left Corner ---//
        for (int i = 0, oj = size - 1; i < size; ++i, --oj) {
            for (int j = 0, oi = size - 1; j < size; ++j, --oi) {
                padded[i][j] = orig[oi][oj];
            }
        }

        // bottom right corner
        //for (int i = nrow + size; i < nrow + (2 * size); ++i) {
        //    for (int j = rows, oj = rows + (2 * size) - 1; j < rows + size; ++j, --oj) {
        //        padded[i][oj] = orig[i][j];
        //    }
        //}

        //---- Right Bottom Corner ---//
        //for(int c=size, x=nrow-1; x>=nrow-size; c--, x--) {
        //        padded[x][x] = orig[rows-c][rows-c];
        //}
        //---- RightTop Corner ---//
        //for(int c=size, i=0, j=ncol-1; i<size; c--, i++, j--) {
        //        padded[i][j] = orig[c-1][cols-c];
        //}
        //---- Left Bottom Corner ---//
        //for(int c=size, i=nrow-1, j=0; j<size; c--, i--, j++) {
        //        padded[i][j] = orig[rows-c][c-1];         
        //}
        return padded;
    }
    /*--------------------------------------------------------------------------------------------*/

    public void displayReflection(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                //---- Top ---//
                if (i == 0) {
                    int a = i + 2;
                    if (a > 9) {
                        a = 9;
                    }
                    System.out.format("%3d ", img[a][j]);

                } //--- Left ----//
                else if ((i != 0) && (j == 0)) {
                    int a = j + 2;
                    System.out.format("%3d ", img[i][a]);

                } //--- Bottom -----//
                else if (i == img.length - 1) {
                    int a = i - 2;
                    System.out.format("%3d ", img[a][j]);

                } //---- Right ----//
                else if ((i > 0) && (j == img.length - 1)) {
                    int a = j - 2;
                    System.out.format("%3d ", img[i][a]);

                } //----- inside ----//
                else {
                    //System.out.format("%3s ", "x");
                    System.out.format("%3d ", img[i][j]);
                }
            }

            System.out.println();

        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] ImageRead(String filename) {

        try {

            // -- read input image
            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            // -- separate image into RGB components
            int red[][] = new int[bi.getHeight()][bi.getWidth()];
            int grn[][] = new int[bi.getHeight()][bi.getWidth()];
            int blu[][] = new int[bi.getHeight()][bi.getWidth()];
            for (int i = 0; i < red.length; ++i) {
                for (int j = 0; j < red[i].length; ++j) {
                    red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    blu[i][j] = bi.getRGB(j, i) & 0xFF;
                }
            }

            return grn;

        } catch (IOException e) {
            System.out.println("image I/O error");
            return null;
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public void FlipVerticaly() throws IOException {

        image = ImageIO.read(new File(input));

        // Flip the image vertically
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/vertical.png");
        ImageIO.write(bi, "png", outputfile);
    }

    /*--------------------------------------------------------------------------------------------*/
    public void FlipHorizontaly() throws IOException {

        image = ImageIO.read(new File(input));

        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/horizontal.png");
        ImageIO.write(bi, "png", outputfile);

    }
    /*--------------------------------------------------------------------------------------------*/

    public void Rotate180() throws IOException {

        image = ImageIO.read(new File(input));

        // Flip the image vertically and horizontally; equivalent to rotating the image 180 degrees
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-image.getWidth(null), -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/rotate180.png");
        ImageIO.write(bi, "png", outputfile);

    }
    /*--------------------------------------------------------------------------------------------*/

}
