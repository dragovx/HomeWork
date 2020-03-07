import java.awt.geom.Rectangle2D;

public class Norm extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range) {
        range.x=-2;
        range.y=-1.5;
        range.height=3;
        range.width=3;
    }

    public int numIterations(double x, double y) {
        double zx = 0;
        double zy = 0;
        double temp;
        int iter = MAX_ITERATIONS;
        while (zx*zx+zy*zy<4 && iter>0){
            temp = zx * zx - zy * zy + x;
            zy = 2 * zx * zy + y;
            zx = temp;
            iter--;
        }
        return MAX_ITERATIONS-iter-1;
    }
}