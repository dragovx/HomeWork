import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size;
    private double zoom,zoom1;
    private JImageDisplay jimage;
    private FractalGenerator fgen = new BurningShip();
    //private FractalGenerator fgen = new Tricorn();
    //private FractalGenerator fgen = new Mandelbrot();
    private Rectangle2D.Double range = new Rectangle2D.Double();

    public FractalExplorer(int size) {
        this.size = size;
        this.zoom = this.zoom1 = 0.8;
        fgen.getInitialRange(range);
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        jfrm.setLocation(100,50);
        JButton jbt = new JButton("Reset");
        JButton jbt1 = new JButton("Save images");
        JLabel jbl = new JLabel("Fractal:");
        JComboBox<String> jcb = new JComboBox<String>();
        JPanel jpl = new JPanel();
        JPanel jpl1 = new JPanel();
        jpl.add(jbt1);
        jpl.add(jbt);
        jpl1.add(jbl);
        jpl1.add(jcb);
        jimage = new JImageDisplay(size,size);jfrm.add(jpl, BorderLayout.SOUTH);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.add(jpl1, BorderLayout.NORTH);
        jcb.addItem("Mandelbrot");
        jcb.addItem("Tricorn");
        jcb.addItem("Burning Ship");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jbt.addActionListener(new TestActionListener());
        jimage.addMouseListener(new TestMouseListener());
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jimage.setVisible(true);
        drawFractal();
        jfrm.pack();
        jimage.repaint();
        jimage.validate();
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
            double mouseX = (mouseEvent.getX()-size/2);
            double mouseY = (mouseEvent.getY()-size/2);
            System.out.println(mouseX + " " + mouseY+ " " + zoom);
            fgen.recenterAndZoomRange(range, ((mouseX / 266)-0.50), (mouseY / 266), zoom);
            zoom*=zoom1;
            jimage.repaint();
            drawFractal();

        }
        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }

    }

    public static void main(String[] args){
        new FractalExplorer(700);
    }

}

