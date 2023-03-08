package fgroupindonesia.printscreen;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author staff
 */
public class ScreenshotTaker implements ClipboardOwner {

    JFrame jframe;

    public ScreenshotTaker(JFrame jf) {
        jframe = jf;
    }

    public void take(int x1, int y1, int x2, int y2) throws Exception {
        jframe.setVisible(false);
        String filename = generateName();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File(filename));

        BufferedImage imageNew = cropTheObject(image, x1, y1, x2, y2);

        filename = generateName();
        ImageIO.write(imageNew, "png", new File(filename));

        TransferableImage trans = new TransferableImage(imageNew);
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(trans, this);

        System.out.println("Screenshot success!");
        //jframe.setVisible(true);
        //showTooltip();
    }

    private BufferedImage cropTheObject(BufferedImage img, int x, int y, int w, int h) {
        return img.getSubimage(x, y, w, h);
    }

    private String generateName() {

        new File(DefaultPath.systemLocation).mkdirs();

        SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMddhhmmss");

        String n = sdf.format(new Date()) + ".png";
        return DefaultPath.systemLocation + File.separator + n;
    }

    public void copyToClipboard(BufferedImage bf) {

    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }

}
