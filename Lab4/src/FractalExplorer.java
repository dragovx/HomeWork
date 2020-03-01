import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int size;
    private JImageDisplay jimage;
    private FractalGenerator fgen = new Mandelbrot();
    private Rectangle2D.Double range = new Rectangle2D.Double();

    public FractalExplorer(int size) {
        this.size = size;
        fgen.getInitialRange(range);
    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        JButton jbt = new JButton("Reset");
        JButton jbt1 = new JButton("Zoom");
        jfrm.add(jbt, BorderLayout.SOUTH);
        jfrm.add(jbt1, BorderLayout.NORTH);
        jimage = new JImageDisplay(size,size);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jbt.addActionListener(new TestActionListener());
        jbt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                fgen.recenterAndZoomRange(range,0.1,0.1,0.9);
                drawFractal();
            }
        });
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
            fgen.getInitialRange(range);
            drawFractal();
        }
    }

    public class TestMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {
            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
            fgen.recenterAndZoomRange(range,mouseX,mouseY,0.5);
        }
        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }

    }

    public static void main(String[] args){
        FractalExplorer f = new FractalExplorer(800);
        f.createAndShowGUI();
        f.drawFractal();

    }

}

