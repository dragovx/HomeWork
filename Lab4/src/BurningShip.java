import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.x=-2;
        range.y=-2.5;
        range.height=4.5;
        range.width=3.5;
    }

    public int numIterations(double x, double y) {
        double x1= 0;
        double y1= 0;
        for(int i=0;i<MAX_ITERATIONS;i++){
            double x2 = x1*x1 - y1*y1 + x;
            y1 = Math.abs(2*x1*y1 + y);
            x1 = Math.abs(x2);
            if (x1*x1+y1*y1>4){
                return i;
            }
        }
        return -1;
    }
}
