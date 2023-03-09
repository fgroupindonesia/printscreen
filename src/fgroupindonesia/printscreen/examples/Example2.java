
package fgroupindonesia.printscreen.examples;

import fgroupindonesia.printscreen.engine.PrintScreen;

/**
 * This example will do several things:
 * a. taking screenshot entirescreen
 * b. autocleanup cache
 * c. copytoclipboard (so you can PASTE afterwards)
 * 
 * @author fgroupindonesia
 */
public class Example2 {
    public static void main(String[] args) {
        
        
        PrintScreen prEngine = new PrintScreen();
        
        prEngine.setSnippingMode(false);
        prEngine.setAutoCleanup(true);
        prEngine.takeCapture();
        
        
    }
}
