package test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    /*---------------------------------------------------------------------*/
    public static void main(String[] args) {
        
        List<Double> data = new ArrayList<>();
        data.add(600.0);
        data.add(470.0);
        data.add(170.0);
        data.add(430.0);
        data.add(300.0);  
        
        
        Statistics stat = new Statistics(data);
        System.out.println("Mean     : " + stat.getMean());
        System.out.println("Variance : " + stat.getVariance());
        System.out.format("StdDev   : %.3f", stat.getStdDev());
        
        double max = stat.getMean() + stat.getStdDev();
        double min = stat.getMean() - stat.getStdDev();
        
        System.out.println("\n------------------------");
        System.out.format("Max : %.2f\n", max);
        System.out.format("Min : %.2f\n", min);

    }
    /*---------------------------------------------------------------------*/
    
}
/*

Mean     : 394.0
Variance : 21704.0
StdDev   : 147.323
------------------------
Max : 541.32
Min : 246.68

*/