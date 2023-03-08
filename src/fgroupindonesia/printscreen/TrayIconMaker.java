package fgroupindonesia.printscreen;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 * @author staff
 */
public class TrayIconMaker {
    
    File logoFile;
    MainArea mainFrame;
    
    private void extractImageFromJar() throws Exception {
        InputStream link = (getClass().getResourceAsStream("/fgroupindonesia/images/logo.png"));
        Files.copy(link, logoFile.getAbsoluteFile().toPath());
        System.out.println("Image logo for PrintScreen was created successfully!");
    }
    
    public TrayIconMaker(File fileImage, MainArea mf) {
        
        try {
            
            mainFrame = mf;
            
            logoFile = fileImage;
            
            if (!fileImage.exists()) {
                extractImageFromJar();
                Thread.sleep(3000);
            }
            
            PopupMenu pop = new PopupMenu();
            TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage(fileImage.getAbsolutePath()));
            trayIcon.setToolTip("PrintScreen");
            trayIcon.setImageAutoSize(true);
            
            SystemTray tray = SystemTray.getSystemTray();
            
            MenuItem exitItem = new MenuItem("exit");
            MenuItem screenshotItem = new MenuItem("screenshot");
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            
            screenshotItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.start();
                }
            });
            
            pop.add(screenshotItem);
            pop.addSeparator();
            pop.add(exitItem);
            trayIcon.setPopupMenu(pop);
            tray.add(trayIcon);
            
        } catch (Exception ex) {
            System.err.println("Error at TrayIconMaker " + ex.getMessage());
        }
        
    }
    
}
