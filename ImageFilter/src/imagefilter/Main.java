package imagefilter;
import median.Median;

public class Main {

    public static void main(String[] args) {
        
        Reflection();
        ProcessMedianFilter();        
    }

    /*--------------------------------------------------------------------------------------------
    Program 4a, 5a,
        Perform reflection image padding at the edges
    --------------------------------------------------------------------------------------------*/
    private static void Reflection() {
        ImageReadWrite image = new ImageReadWrite();
        int[][] gray1 = image.ImageRead("src/image/Lenna.png");
        
        ImageReflection ref = new ImageReflection();      
        int[][] gray2 = ref.reflection(gray1, 1);
        int[][] gray3 = ref.makeReflection(gray2, 3);
        //ref.displayImage(gray3);
        ref.writeImage(gray2, "src/image/LennaReflectionPadding.png");
    }   
    /*--------------------------------------------------------------------------------------------
    Program 4b.)
        Perform a 3x3 median filter operation on the green component 
      --------------------------------------------------------------------------------------------*/
    private static void ProcessMedianFilter() {
        Median median = new Median();
        median.process("src/image/LennaReflectionPadding.png", "src/image/LennaMedian.png");
    }   
    /*--------------------------------------------------------------------------------------------*/
}
