
package fgroupindonesia.printscreen.examples;

import fgroupindonesia.printscreen.engine.PrintScreen;

/**
 * System : JavaPrintScreen
 * Description : 
 * This example will do several things:
 * a. taking screenshot snipping
 * b. always ask directory where to save
 * c. copytoclipboard (so you can PASTE afterwards)
 * 
 * @author fgroupindonesia
 */
public class Example3 {
    public static void main(String[] args) {
        
        
        PrintScreen prEngine = new PrintScreen();
        
        prEngine.setSnippingMode(true);
        prEngine.setAlwaysAskDirectory(true);
        prEngine.takeCapture();
        
        
    }
}
