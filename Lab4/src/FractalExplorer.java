import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
        import java.awt.event.*;
        import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Boolean.*;

/**
 @author dragovx
 */

public class FractalExplorer {
    private Rectangle2D.Double range = new Rectangle2D.Double();
    private int size;
    private int rowsremaining;
    private double zoom;
    private JFrame jfrm;
    private JButton jbt,jbt1;
    private JComboBox <Object> jcb;
    private JImageDisplay jimage;
    private FractalGenerator fgen;

    public FractalExplorer(int size) {
        this.size = size;
        this.zoom  = 0.5;
        fgen = new Mandelbrot();
        fgen.getInitialRange(range);
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        jfrm = new JFrame("Fractal");
        jbt = new JButton("Reset");
        jbt1 = new JButton("Save images");
        JLabel jbl = new JLabel("Fractal:");
        jcb = new JComboBox<>();
        JPanel jpl = new JPanel();
        JPanel jpl1 = new JPanel();
        jimage = new JImageDisplay(size,size);
        jfrm.setLocation(100,50);
        jpl.add(jbt1); jpl.add(jbt);
        jpl1.add(jbl); jpl1.add(jcb);
        jfrm.add(jpl, BorderLayout.SOUTH);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.add(jpl1, BorderLayout.NORTH);
        jcb.addItem(new Mandelbrot());
        jcb.addItem(new Tricorn());
        jcb.addItem(new BurningShip());
        jbt.addActionListener(new TestActionListener());
        jimage.addMouseListener(new TestMouseListener());
        jbt1.addActionListener(new TestActionListener());
        jcb.addActionListener(new TestActionListener());
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jfrm.pack();
        jimage.validate();
        drawFractal();
    }

    private void drawFractal() {
        enableUI(FALSE);
        rowsremaining=size;
        for (int y = 0; y < size; y++) {
            new FractalWorker(y).execute();
        }
    }

    public void enableUI(boolean val){
        if (val==TRUE){
            jfrm.setEnabled(TRUE);
        } else {
            jfrm.setEnabled(FALSE);
        }
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == jcb) {
                fgen = (FractalGenerator) jcb.getSelectedItem();
                fgen.getInitialRange(range);
                drawFractal();
            } else if (ae.getSource() == jbt) {
                jimage.clearImage();
                fgen.getInitialRange(range);
                drawFractal();
            } else if (ae.getSource() == jbt1) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "PNG"));
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        BufferedImage image = new BufferedImage(jimage.getHeight(), jimage.getWidth(), BufferedImage.TYPE_INT_RGB);
                        jimage.paint(image.getGraphics());
                        ImageIO.write(image, "PNG", new File(file.getAbsolutePath() + ".PNG"));
                    } catch (IOException ex) {
                        System.out.println("Failed to save image!");
                    }
                } else {
                    System.out.println("No file chosen!");
                }
            }
        }
    }

    public class TestMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {
            double mouseX = FractalGenerator.getCoord(range.x, range.x + range.width, size, mouseEvent.getX());
            double mouseY = FractalGenerator.getCoord(range.y, range.y + range.width, size, mouseEvent.getY());
            if(mouseEvent.getButton()==MouseEvent.BUTTON1) {
                fgen.recenterAndZoomRange(range, mouseX, mouseY, zoom);
                drawFractal();
            } else if (mouseEvent.getButton()==MouseEvent.BUTTON3){
                fgen.recenterAndZoomRange(range, mouseX, mouseY, 1/zoom);
                drawFractal();
            }
        }
        public void mousePressed(MouseEvent mouseEvent) { }
        public void mouseReleased(MouseEvent mouseEvent) { }
        public void mouseEntered(MouseEvent mouseEvent) { }
        public void mouseExited(MouseEvent mouseEvent) { }

    }

    private class FractalWorker extends SwingWorker<Object,Object> {
        int y;
        int[] masx;

        public FractalWorker(int y){
            this.y=y;
        }

        protected Object doInBackground() {
            masx = new int[size];
            for (int x = 0; x < size; x++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width,
                        size, x);
                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height,
                        size, y);
                if (fgen.numIterations(xCoord, yCoord) == -1) {
                    masx[x] = 0;
                } else {
                    float hue = 0.7f + (float) fgen.numIterations(xCoord, yCoord) / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    masx[x] = rgbColor;
                }
            }
            return null;
        }

        protected void done() {
            for (int x = 0; x < size; x++) {
                jimage.drawPixel(x, y, masx[x]);
            }
            jimage.repaint(0, 0, y, size, 1);
            rowsremaining--;
            if (rowsremaining==0){
                enableUI(TRUE);
            }
            super.done();
        }
    }

    public static void main(String[] args){ new FractalExplorer(700); }

}