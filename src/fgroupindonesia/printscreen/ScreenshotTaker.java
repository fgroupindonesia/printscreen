package fgroupindonesia.printscreen;

import fgroupindonesia.printscreen.engine.EngineStates;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import fgroupindonesia.printscreen.engine.PrintScreen.PSImageFormat;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    String filename = null;

    

    public ScreenshotTaker() {

    }

    public ScreenshotTaker(JFrame jf) {
        jframe = jf;
    }

    CleanupCache cleaner = new CleanupCache();

    public void cleanCacheExcept(String except) {
        try {
            cleaner.exceptFile("logo.png");
            cleaner.exceptFile(except);

            Thread.sleep(3000);
        } catch (Exception ex) {
            System.err.println("Error at cleanCache()" + ex.getMessage());
        }

    }

    public BufferedImage screenshotEntireScreen() {

        filename = generateName();
        BufferedImage image = null;

        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRectangle = new Rectangle(screenSize);
            Robot robot = new Robot();
            image = robot.createScreenCapture(screenRectangle);
            writeImageAsFormat(image);
            //ImageIO.write(image, "png", new File(filename));

            if (EngineStates.copy_to_clipboard) {
                copyToClipboard(image);
            }

            cleanCacheExcept(filename);
            System.out.println("Screenshot success!");
            
        } catch (Exception e) {
            System.err.println("Error at screenshotEntireScreen()" + e.getMessage());
        }

        return image;
    }

    public void screenshotSnipScreen() {

        // call the JFrame for snipping from screen
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jframe = new MainArea();
                jframe.setVisible(true);

            }
        });

    }

    private void writeImageAsFormat(BufferedImage img) {

        try {
            if (EngineStates.imageformat == PSImageFormat.PNG) {
                ImageIO.write(img, "png", new File(filename));
            } else if (EngineStates.imageformat == PSImageFormat.JPG) {
                ImageIO.write(img, "jpg", new File(filename));
            } else {
                writeAsGreyscale(img);
            }
        } catch (Exception e) {
            System.err.println("Error at writeImageAsFormat() " + e.getMessage());
        }
    }

    private void writeAsGreyscale(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Retrieving the values of a pixel
                int pixel = img.getRGB(x, y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                //Finding the average of the red green blue values
                int average = (red + green + blue) / 3;
                //Creating new Color object
                color = new Color(average, average, average);
                //Setting new Color object to the image
                img.setRGB(x, y, color.getRGB());
            }
        }
        //Saving the modified image
        filename = generateName();

        try {
            ImageIO.write(img, "jpg", new File(filename));
        } catch (Exception e) {
            System.err.println("Error at writeAsGreyscale() " + e.getMessage());
        }
    }

    public void take(int x1, int y1, int x2, int y2) throws Exception {
        jframe.setVisible(false);

        BufferedImage image = screenshotEntireScreen();
        BufferedImage imageNew = cropTheObject(image, x1, y1, x2, y2);

        writeImageAsFormat(imageNew);

        if (EngineStates.copy_to_clipboard) {
            copyToClipboard(imageNew);
        }

        cleanCacheExcept(filename);
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

    public void copyToClipboard(BufferedImage imageNew) {
        TransferableImage trans = new TransferableImage(imageNew);
        Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
        c.setContents(trans, this);
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }

}
