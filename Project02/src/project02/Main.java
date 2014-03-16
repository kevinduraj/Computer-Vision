package project02;

public class Main {

    public static void main(String[] args) {

        System.out.println("Project 02 - Histogram");
        MakeCutoffImage();
        
        //Histogram hist2 = new Histogram();
        //hist2.readHistogram("src/image2/LennaCutoff.png");
        //hist2.displayHistogram();
             
    }

    private static void MakeCutoffImage() {
        /*---------------- Loading Data -------------------------*/
        Histogram hist = new Histogram();
        hist.readHistogram("src/image2/Lenna.png");
        hist.setCutoff(10.0); 
        /*-------------------------------------------------------*/
        hist.displayCutoffHistogram();
        hist.displayAll();
        /*-------------------------------------------------------*/
        //hist.setStretch();
        //hist.displayStretch();
        /*-------------------------------------------------------*/
        //int grn[][] = hist.ImageRead("src/image2/Lenna.png");
        //hist.ImageWrite(grn, "src/image2/LennaCutoff.png");
    }
}
