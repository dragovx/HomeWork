import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.x=-2;
        range.y=-2;
        range.height=4;
        range.width=4;
    }

    public int numIterations(double x, double y) {
        double x1= x;
        double y1= y;
        for(int i=0;i<MAX_ITERATIONS;i++){
            double x2=x1*x1-y1*y1+x;
            double y2=-2*x1*y1+y;
            x1=x2;
            y1=y2;
            if (x1*x1+y1*y1>4){
                return i;
            }
        }
        return -1;
    }
}