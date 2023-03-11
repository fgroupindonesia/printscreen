
package fgroupindonesia.printscreen.examples;

import fgroupindonesia.printscreen.engine.PrintScreen;

/**
 * System : JavaPrintScreen
 * Description : 
 * This example will do several things:
 * a. taking screenshot snipping
 * b. set the directory location programatically
 * c. copytoclipboard (so you can PASTE afterwards)
 * 
 * @author fgroupindonesia
 */
public class Example4 {
    public static void main(String[] args) {
        
        
        PrintScreen prEngine = new PrintScreen();
        
        prEngine.setSnippingMode(true);
        prEngine.setDirectoryPath("C:\\ProgramData");
        prEngine.takeCapture();
        
        
    }
}
