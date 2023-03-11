package fgroupindonesia.printscreen.engine;

import fgroupindonesia.printscreen.EngineStates;
import fgroupindonesia.printscreen.MainArea;
import fgroupindonesia.printscreen.ScreenshotTaker;
import java.io.File;

/**
 * System : JavaPrintScreen
 * Description : 
 * This file will help everyone for using the library
 * just as mentioned in github readme file.
 * https://github.com/fgroupindonesia/printscreen
 * 
 * @author fgroupindonesia
 */
public class PrintScreen {

    public enum PSImageFormat {
        JPG,
        PNG,
        GREYSCALE
    };

    public void start() {
        // this will run the tray only
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                mainArea = new MainArea(staker);
                mainArea.setVisible(false);
                staker.setReferenceMainArea(mainArea);
                
            }
        });
    }

    public void setImageFormat(PSImageFormat pf) {
        EngineStates.imageformat = pf;
    }

    MainArea mainArea;
    ScreenshotTaker staker = new ScreenshotTaker();

    public void takeCapture() {

        if (!isSnippingMode()) {
            staker.screenshotEntireScreen();
        } else {
            staker.screenshotSnipScreen();
        }
    }

    public boolean isSnippingMode() {
        return EngineStates.snipping_mode;
    }

    public void setSnippingMode(boolean b) {
        EngineStates.snipping_mode = b;
    }

    public void setLogoVisibility(boolean b) {
        EngineStates.logo_visibility = b;
    }

    public void setLogo(File fileAbsolutePath) {
        EngineStates.logo_absolute_path = fileAbsolutePath;
    }

    public void setDirectoryPath(String folderLocation) {
        EngineStates.directory_path = folderLocation;
    }

    public void setAlwaysAskDirectory(boolean b) {
        EngineStates.always_ask_directory = b;

    }

    public boolean isAlwaysAskDirectory() {
        return EngineStates.always_ask_directory;
    }

    public boolean isAutoCleanup() {
        return EngineStates.auto_cleanup;
    }

    public void setOpenSavedDirectoryFeature(boolean n) {
        EngineStates.open_saved_directory = n;
    }

    public boolean isOpenSavedDirectoryFeature() {
        return EngineStates.open_saved_directory;
    }

    public void setAutoCleanup(boolean b) {
        EngineStates.auto_cleanup = b;
    }

    public void setCopyToClipboard(boolean b) {
        EngineStates.copy_to_clipboard = b;
    }

    public boolean isCopyToClipboard() {
        return EngineStates.copy_to_clipboard;
    }
}
