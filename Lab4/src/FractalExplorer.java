import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size;
    private JImageDisplay jimage = new JImageDisplay(800,800);
    private FractalGenerator fgen = new Mandelbrot();
    private Rectangle2D.Double range = new Rectangle2D.Double();

    public FractalExplorer(int size) {
        this.size = size;


    }

    public static void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        JButton jbt = new JButton("СБРОС СУКА!!!!");
        jfrm.add(jbt, BorderLayout.SOUTH);
        jfrm.add(new JImageDisplay(800, 800), BorderLayout.CENTER);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
        jfrm.setResizable(false);


    }

    private void drawFractal() {
        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
                        size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
                        size, y);
                if (fgen.numIterations(xCoord, yCoord) == -1) {
                    jimage.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) fgen.numIterations(xCoord, yCoord) / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    jimage.drawPixel(x, y, rgbColor);
                }
            }
        }
        jimage.repaint();
    }

    public class TestActionListener implements ActionListener {
            public void actionPerformed(ActionEvent ae){
                jimage.clearImage();
                drawFractal();
            }
    }

    public class TestMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {
            fgen.recenterAndZoomRange(range,25,25,0.5);
        }
        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }
    }

    public static void main(String[] args){
        FractalExplorer f = new FractalExplorer(800);
        createAndShowGUI();
        f.drawFractal();
    }
}

