package project02;

public class Main {

    public static void main(String[] args) {

        System.out.println("Project 02");

        /*---------------- Loading Data -------------------------*/
        Histogram histogram = new Histogram();
        histogram.readHistogram("src/image2/Lenna.png");
        histogram.setCutoff(10.0);
        //histogram.displayHistogram(); 
        /*-------------------------------------------------------*/
        histogram.displayAll();
        histogram.cutoffHistogram();
        histogram.displayCutoff();
        /*-------------------------------------------------------*/
             
    }
}
