import javax.swing.*;
import java.awt.*;
import java.util.Random;

class PaintPanel extends JPanel{
    Insets ins;
    Random rand;
    PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
        rand = new Random();
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int x,y,x2,y2;
        int height = getHeight();
        int width = getWidth();
        ins = getInsets();
        x=rand.nextInt(width-ins.left);
        y=rand.nextInt(height-ins.bottom);
        int xS=x;
        int yS=y;
        x2=rand.nextInt(width-ins.left);
        y2=rand.nextInt(height-ins.bottom);
        g.drawLine(x,y,x2,y2);
        for(int i=1;i<10;i++){
            x=x2;
            y=y2;
            x2=rand.nextInt(width-ins.left);
            y2=rand.nextInt(height-ins.bottom);
            g.drawLine(x,y,x2,y2);
        }
        g.drawLine(x2,y2,xS,yS);
    }
}

class PaintDemo{
    JLabel jlab;
    PaintPanel pp;
    PaintDemo(){
        JFrame jfrm = new JFrame("Paint Demo");
        jfrm.setSize(300,300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pp = new PaintPanel();
        JLabel jlab = new JLabel("Фигня");
        jfrm.add(pp);
        jfrm.setVisible(true);
    }
    public static void main(String args[]){
                new PaintDemo();
            }
    }
