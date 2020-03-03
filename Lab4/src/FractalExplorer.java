import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.awt.geom.Rectangle2D;
import java.util.prefs.Preferences;

public class FractalExplorer {
    private int size;
    private Point p = new Point();
    private JImageDisplay jimage;
    private int zoom;
    private FractalGenerator fgen = new Mandelbrot();
    private Rectangle2D.Double range = new Rectangle2D.Double();

    public FractalExplorer(int size) {
        this.size = size;
        fgen.getInitialRange(range);
    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        JButton jbt = new JButton("Reset");
        jfrm.add(jbt, BorderLayout.SOUTH);
        jimage = new JImageDisplay(size,size);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setLocation(100,50);
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jbt.addActionListener(new TestActionListener());
        jimage.addMouseListener(new TestMouseListener());
    }

    private void drawFractal() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
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
            fgen.getInitialRange(range);
            drawFractal();
        }
    }

    public class TestMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {
            double mouseX = mouseEvent.getX();
            double mouseY = mouseEvent.getY();
            System.out.println(mouseX + mouseY);
            int i=1;
            fgen.recenterAndZoomRange(range,  -2-(mouseX/400)*Math.pow(1.1,i),-1.5 -(mouseY/400)*Math.pow(1.1,i),1.1);
            i++;
            drawFractal();
        }
        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }

    }

    public static void main(String[] args){
        FractalExplorer f = new FractalExplorer(700);
        f.createAndShowGUI();
        f.drawFractal();

    }

}

