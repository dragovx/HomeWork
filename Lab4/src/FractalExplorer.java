import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.awt.geom.Rectangle2D;
import java.util.prefs.Preferences;

public class FractalExplorer {
    private int size;
    private JImageDisplay jimage;
    private double zoom;
    private FractalGenerator fgen = new BurningShip();
    //private FractalGenerator fgen = new Tricorn();
    //private FractalGenerator fgen = new Mandelbrot();
    private Rectangle2D.Double range = new Rectangle2D.Double();
    private int i;

    public FractalExplorer(int size) {
        this.size = size;
        fgen.getInitialRange(range);
    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        JButton jbt = new JButton("Reset");
        JButton jbt1 = new JButton("Save images");
        JLabel jbl = new JLabel("Fractal:");
        JPanel jpl = new JPanel();
        JPanel jpl1 = new JPanel();
        jpl.add(jbt1);
        jpl.add(jbt);
        JComboBox<String> jcb = new JComboBox<String>();
        jpl1.add(jbl);
        jpl1.add(jcb);
        jfrm.add(jpl, BorderLayout.SOUTH);
        jimage = new JImageDisplay(size,size);
        jfrm.add(jimage, BorderLayout.CENTER);
        jcb.addItem("Mandelbrot");
        jcb.addItem("Tricorn");
        jcb.addItem("Burning Ship");
        jfrm.add(jpl1, BorderLayout.NORTH);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setLocation(100,50);
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jbt.addActionListener(new TestActionListener());
        jimage.addMouseListener(new TestMouseListener());
        zoom=0.8;

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
            i=0;
            drawFractal();
        }
    }

    public class TestMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {
            double mouseX = (mouseEvent.getX()-size/2);
            double mouseY = (mouseEvent.getY()-size/2);
            System.out.println(mouseX + " " + mouseY);
            fgen.recenterAndZoomRange(range, ((mouseX / 266)-0.55), (mouseY / 266), zoom);
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

