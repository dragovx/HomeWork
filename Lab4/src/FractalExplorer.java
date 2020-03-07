import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
        import java.awt.event.*;
        import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FractalExplorer {
    private int size;
    private double centerX=0;
    private double centerY=0;
    private double zoom;
    private JImageDisplay jimage;
    private FractalGenerator fgen;
    private Rectangle2D.Double range = new Rectangle2D.Double();

    public FractalExplorer(int size) {
        this.size = size;
        this.zoom  = 0.3;
        fgen = new Mandelbrot();
        fgen.getInitialRange(range);
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        JFrame jfrm = new JFrame("Fractal");
        jfrm.setLocation(100,50);
        JButton jbt = new JButton("Reset");
        JButton jbt1 = new JButton("Save images");
        JLabel jbl = new JLabel("Fractal:");
        JComboBox<String> jcb = new JComboBox<>();
        JPanel jpl = new JPanel();
        JPanel jpl1 = new JPanel();
        jpl.add(jbt1);
        jpl.add(jbt);
        jpl1.add(jbl);
        jpl1.add(jcb);
        jimage = new JImageDisplay(size,size);
        jfrm.add(jpl, BorderLayout.SOUTH);
        jfrm.add(jimage, BorderLayout.CENTER);
        jfrm.add(jpl1, BorderLayout.NORTH);
        jcb.addItem("Mandelbrot");
        jcb.addItem("Tricorn");
        jcb.addItem("Burning Ship");
        jcb.addItem("Norm");
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jbt.addActionListener(new TestActionListener());
        jbt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "PNG");
                chooser.setFileFilter(filter);
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
        });
        jimage.addMouseListener(new TestMouseListener());
        jcb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String src = (String) jcb.getSelectedItem();
                switch (src){
                    case "Mandelbrot":
                        fgen=new Mandelbrot();
                        break;
                    case "Tricorn":
                        fgen=new Tricorn();
                        break;
                    case "Burning Ship":
                        fgen=new BurningShip();
                        break;
                    case "Norm":
                        fgen=new Norm();
                        break;
                }
                jimage.clearImage();
                fgen.getInitialRange(range);
                drawFractal();
                centerX=centerY=0;
            }
        });
        jfrm.setVisible(true);
        jfrm.setResizable(false);
        jimage.setVisible(true);
        drawFractal();
        jfrm.pack();
        jimage.validate();


    }

    private void drawFractal() {
        for (int y = 0; y < size; y++) {
            FractalWorker fw = new FractalWorker(y);
            fw.execute();
        }
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae){
            jimage.clearImage();
            fgen.getInitialRange(range);
            drawFractal();
            centerX=centerY=0;
        }
    }

    public class TestMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent mouseEvent) {
            double mouseX = (mouseEvent.getX()-(double) size/2)/(size/range.width);
            double mouseY = (mouseEvent.getY()-(double) size/2)/(size/range.height);
            fgen.recenterAndZoomRange(range, centerX+mouseX,centerY+mouseY, zoom);
            centerX=centerX+mouseX;
            centerY=centerY+mouseY;
            drawFractal();

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
            super.done();
        }
    }

    public static void main(String[] args){ new FractalExplorer(700);
    }

}