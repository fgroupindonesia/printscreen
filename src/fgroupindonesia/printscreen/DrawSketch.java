package fgroupindonesia.printscreen;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawSketch extends JPanel implements MouseMotionListener {

    ScreenshotTaker sctaker;
    JFrame jframe;
    public int x1, x2, y1, y2;

    public DrawSketch(JFrame ref, ScreenshotTaker staker) {

        jframe = ref;
        sctaker = staker;
        
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent m) {
                x2 = m.getXOnScreen();
                y2 = m.getYOnScreen();

                clearNow = false;
                repaint();
            }

        });

        addMouseListener(new MouseAdapter() {

            @Override

            public void mousePressed(MouseEvent evt) {

                x1 = evt.getXOnScreen();
                y1 = evt.getYOnScreen();

            }

            @Override
            public void mouseClicked(MouseEvent evt) {

            }

            @Override
            public void mouseReleased(MouseEvent m) {

                clearNow = false;
                repaint();

                try {
                    sctaker.take(x1, y1, x2-x1, y2-y1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.err.println("Error at screenshot! " + ex.getMessage());
                }
            }

        });

        addMouseMotionListener(this);
    }

    boolean clearNow = false;

    public void clearAll() {
        clearNow = true;
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (!clearNow) {
            
            g.drawRect(x1, y1, x2-x1, y2-y1);
        }

    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    @Override
    public void mouseDragged(MouseEvent event) {

        x2 = event.getX();
        y2 = event.getY();
        repaint();

    }

}
