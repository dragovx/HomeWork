import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class JImageDisplay extends JComponent {
    BufferedImage image;

    public JImageDisplay(int width, int height){
        image =new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new Dimension(width, height));
    }
    public void paintComponent (Graphics g){
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage() {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                image.setRGB(x,y,0);
            }
        }
    }

    public void  drawPixel (int x, int y, int rgbColor){
        image.setRGB(x,y, rgbColor);
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
}