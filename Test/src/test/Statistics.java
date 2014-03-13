package test;

import java.util.Arrays;
import java.util.List;

public class Statistics {

    List<Double> data;
    double size;

    /*----------------------------------------------------------------------*/
    public Statistics(List<Double> data) {
        this.data = data;
        size = data.size();
    }

    /*----------------------------------------------------------------------*/
    double getMean() {
        
        double sum = 0.0;
        for (double a : data) {
            sum += a;
        }
        return sum / size;
    }

    /*----------------------------------------------------------------------*/
    double getVariance() {
        
        double mean = getMean();
        double temp = 0;
        for (double a : data) {
            temp += (mean - a) * (mean - a);
        }
        return temp / size;
    }

    /*----------------------------------------------------------------------*/
    double getStdDev() {
        return Math.sqrt(getVariance());
    }

    /*----------------------------------------------------------------------*/
    public double median() {
        double[] b = new double[data.size()];
        System.arraycopy(data, 0, b, 0, b.length);
        Arrays.sort(b);

        if (data.size() % 2 == 0) {
            return (b[(b.length / 2) - 1] + b[b.length / 2]) / 2.0;
        } else {
            return b[b.length / 2];
        }
    }
    /*----------------------------------------------------------------------*/
}
