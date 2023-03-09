
package fgroupindonesia.printscreen.examples;

import fgroupindonesia.printscreen.engine.PrintScreen;

/**
 * This example will do quick things:
 * a. taking screenshot snipping mode
 * b. open the path image saved
 * 
 * @author fgroupindonesia
 */
public class Example1 {
    public static void main(String[] args) {
        
        
        PrintScreen prEngine = new PrintScreen();
        
        // incase you dont want to open the dir
        // prEngine.setOpenSavedDirectoryFeature(false);
        prEngine.takeCapture();
        
        
    }
}
