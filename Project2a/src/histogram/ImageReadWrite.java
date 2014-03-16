package histogram;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageReadWrite {
	
	public  int[][] ImageRead(String filename) {

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

	public void ImageWrite(int img[][], String filename)
	{
		try {
			BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);
	    	
	    	// -- prepare output image
	    	for (int i = 0; i < bi.getHeight(); ++i) {
	    	    for (int j = 0; j < bi.getWidth(); ++j) {
	    	    	int val = img[i][j];
	    			int pixel =	(val << 16) | (val << 8) | (val);
	    			bi.setRGB(j, i, pixel);
	    		}
	    	}
	
	    	// -- write output image
	    	File outputfile = new File(filename);
	    	ImageIO.write(bi, "png", outputfile);	
		}
		catch (IOException e) {

		}
	}
    /*--------------------------------------------------------------------------------------------*/
    void WriteStretchedImage(int[][] img, int first, int last, int[] lookup, String filename) {
        
        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {

                    int val = img[i][j];             
                    
                    if(val < first)  val = 0;
                    else if(val>last) val = 255;
                    else {
                        if(lookup.length > 0) {
                            val = lookup[val];
                        }
                    }

                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            //--- Write output image
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
