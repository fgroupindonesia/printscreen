package fgroupindonesia.printscreen;

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
import fgroupindonesia.printscreen.engine.PrintScreen.PSImageFormat;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * System : JavaPrintScreen
 * Description : 
 * This file is used to record the screen and cropping the image,
 * helping the user to choose directory, and so on.
 *
 * @author fgroupindonesia
 *
 */

public class ScreenshotTaker implements ClipboardOwner {

    MainArea jframe;
    String filename = null;
    JFileChooser jfilechooser;

    private void prepareFileChooser() {
        jfilechooser = new JFileChooser();
        jfilechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        jfilechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (EngineStates.imageformat == PSImageFormat.PNG) {
            jfilechooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG File", "png"));
        } else if (EngineStates.imageformat == PSImageFormat.JPG) {
            jfilechooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG File", "jpg"));
        } else {
            jfilechooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG File", "jpeg"));
        }

        jfilechooser.setAcceptAllFileFilterUsed(true);

    }

    private String getExtensionsFromEngineStates() {
        String extNa = null;

        if (EngineStates.imageformat == PSImageFormat.PNG) {
            extNa = ".png";
        } else if (EngineStates.imageformat == PSImageFormat.JPG) {
            extNa = ".jpg";
        } else {
            extNa = ".jpeg";
        }

        return extNa;
    }

    private File setExtensionAccordingly(File fileIn) {
        // sometimes the user forgot to type the extension
        File newOne = null;
        if (!fileIn.getName().contains(".png") || !fileIn.getName().contains(".jpg") || !fileIn.getName().contains(".jpeg")) {
            String ext = getExtensionsFromEngineStates();
            String fnew = fileIn.getParent() + File.separator + fileIn.getName() + ext;
            newOne = new File(fnew);
        }

        return newOne;
    }

    File fileTargetCustomSave;
    private void showSaveFileDirectory() {
        int hasil = jfilechooser.showSaveDialog(null);

        if (hasil == JFileChooser.APPROVE_OPTION) {

            // set the directory to this one
            EngineStates.directory_path = jfilechooser.getSelectedFile().getParent();

            fileTargetCustomSave = jfilechooser.getSelectedFile();

            // System.out.println("1 as " + target);
            fileTargetCustomSave = setExtensionAccordingly(fileTargetCustomSave);

            // copy from cache to this
            System.out.println("Saved as " + fileTargetCustomSave);
            copyData(new File(filename), fileTargetCustomSave);
        }
    }

    private void copyData(File fileIn, File fileOut) {
        FileInputStream ips = null;
        FileOutputStream fos = null;

        try {

            ips = new FileInputStream(fileIn);
            fos = new FileOutputStream(fileOut);

            int i;

            while ((i = ips.read()) != -1) {
                fos.write(i);
            }

        } catch (Exception ex) {
            System.err.println("Error at copyData() while " + ex.getMessage());
        } finally {

            try {
                if (ips != null) {
                    ips.close();
                }

                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ex) {
                System.err.println("Error at copyData() final " + ex.getMessage());
            }
        }
    }

    public void setReferenceMainArea(MainArea mf){
        jframe = mf;
    }
    
    public ScreenshotTaker() {
        prepareFileChooser();
    }

    CleanupCache cleaner = new CleanupCache();

    public void cleanCacheExcept(String except) {
        try {
            cleaner.exceptFile("logo.png");
            cleaner.exceptFile(except);

            Thread.sleep(3000);

            cleaner.cleanAll();
        } catch (Exception ex) {
            System.err.println("Error at cleanCache()" + ex.getMessage());
        }

    }

    File newFile = null;
    
    private void saveAccordingly() {
        if (EngineStates.always_ask_directory) {
            // when user loves to be asked which directory and file location to be saved?
            showSaveFileDirectory();
        } else if (EngineStates.directory_path != null) {
            if (!EngineStates.directory_path.equalsIgnoreCase(EngineStates.default_directory_path)) {
                // when the directory is set by user programatically
                File originally = new File(filename);
                String newUserLocation = EngineStates.directory_path + File.separator + originally.getName();
                newFile = new File(newUserLocation);
                copyData(originally, newFile);
            }

        }
    }

    public BufferedImage screenshotEntireScreen(boolean openDir) {

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

            //saveAccordingly();
            cleanAccordingly();

            if (openDir) {
                openDirectoryAfterwards();
            }

            System.out.println("Screenshot success!");

        } catch (Exception e) {
            System.err.println("Error at screenshotEntireScreen()" + e.getMessage());
        }

        return image;
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

            saveAccordingly();

            cleanAccordingly();

            if (EngineStates.open_saved_directory) {
                openDirectoryAfterwards();
            }

            System.out.println("Screenshot screenshotEntireScreen success!");

        } catch (Exception e) {
            System.err.println("Error at screenshotEntireScreen()" + e.getMessage());
        }

        return image;
    }

    private void cleanAccordingly() {
        if (EngineStates.auto_cleanup) {
            cleanCacheExcept(filename);
        }
    }

    public void screenshotSnipScreen() {

        // call the JFrame for snipping from screen
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jframe = new MainArea(ScreenshotTaker.this);
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
            ImageIO.write(img, "jpeg", new File(filename));
        } catch (Exception e) {
            System.err.println("Error at writeAsGreyscale() " + e.getMessage());
        }
    }

    public void take(int x1, int y1, int x2, int y2) throws Exception {
        jframe.setVisible(false);

        BufferedImage image = screenshotEntireScreen(false);
        BufferedImage imageNew = cropTheObject(image, x1, y1, x2, y2);

        writeImageAsFormat(imageNew);

        if (EngineStates.copy_to_clipboard) {
            copyToClipboard(imageNew);
        }

        saveAccordingly();

        cleanAccordingly();

        if (EngineStates.open_saved_directory) {
            openDirectoryAfterwards();
        }

        System.out.println("Screenshot take success!");
        //jframe.setVisible(true);
        //showTooltip();
    }

    private BufferedImage cropTheObject(BufferedImage img, int x, int y, int w, int h) {
        return img.getSubimage(x, y, w, h);
    }

    private void openDirectoryAfterwards() {

        try {
            if (EngineStates.always_ask_directory) {
                Runtime.getRuntime().exec("explorer /select," + EngineStates.directory_path + File.separator + fileTargetCustomSave.getName());
            } else if (EngineStates.directory_path != null) {
                System.out.println("Opening ... " + newFile);
                Runtime.getRuntime().exec("explorer /select," + newFile);
            } else {
                Runtime.getRuntime().exec("explorer /select," + filename);
            }

        } catch (Exception e) {
            System.err.print("Error at openDirectoryAfterwards() " + e.getMessage());
        }

    }

    private String generateName() {

        new File(DefaultPath.systemLocation).mkdirs();

        SimpleDateFormat sdf = new SimpleDateFormat("_yyyyMMddhhmmss");

        String ext = null;

        if (EngineStates.imageformat == PSImageFormat.JPG) {
            ext = ".jpg";
        } else if (EngineStates.imageformat == PSImageFormat.GREYSCALE) {
            ext = ".jpeg";
        } else if (EngineStates.imageformat == PSImageFormat.PNG) {
            ext = ".png";
        }

        String n = sdf.format(new Date()) + ext;

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
