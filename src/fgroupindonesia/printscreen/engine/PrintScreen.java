package fgroupindonesia.printscreen.engine;

import fgroupindonesia.printscreen.ScreenshotTaker;
import java.io.File;

/**
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
            
    }

    public void setImageFormat(PSImageFormat pf){
        EngineStates.imageformat = pf;
    }
    
    public void takeCapture() {
        ScreenshotTaker taker = new ScreenshotTaker();

        if (!isSnippingMode()) {
            taker.screenshotEntireScreen();
        } else {
            taker.screenshotSnipScreen();
        }
    }

    public boolean isSnippingMode() {
        return EngineStates.snipping_mode;
    }

    public void setSnippingMode(boolean b) {
        EngineStates.snipping_mode = b;
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

    public boolean isAutoCleanup() {
        return EngineStates.auto_cleanup;
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
