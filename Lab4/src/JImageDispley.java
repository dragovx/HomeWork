import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class JImageDispley extends JComponent {
    private int Width;
    private int Heigth;
    private BufferedImage image;

    public JImageDispley(int x, int y) {
        this.Width = x;
        this.Heigth = y;
        image = new BufferedImage(Width, Heigth, TYPE_INT_RGB);
        Dimension Dim = new Dimension(Width, Heigth);
        setPreferredSize(Dim);
    }

    public void paintComponent (Graphics g){
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage (){
        for (int i=0;i<=Width;i++){
            for (int j=0;j<=Heigth;j++){
                image.setRGB(i, j,0);
            }
        }
    }

    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x,y,rgbColor);
    }
}