package project02;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Project 02");

        /*---------------- Loading Data -------------------------*/
        Histogram histogram = new Histogram();
        histogram.readHistogram("src/image2/Lenna.png");
        histogram.displayHistogram();
    }
}
