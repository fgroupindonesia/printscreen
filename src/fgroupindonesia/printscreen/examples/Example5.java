
package fgroupindonesia.printscreen.examples;

import fgroupindonesia.printscreen.engine.PrintScreen;
import java.io.File;

/**
 * System : JavaPrintScreen
 * Description : 
 * This example will do several things:
 * a. run the app from tray
 * 
 * @author fgroupindonesia
 */
public class Example5 {
    public static void main(String[] args) {
        
        PrintScreen prEngine = new PrintScreen();
        
        prEngine.setLogo(new File("D:\\bird.png"));
        prEngine.start();
    }
}
