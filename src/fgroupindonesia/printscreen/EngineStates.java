package fgroupindonesia.printscreen;

import fgroupindonesia.printscreen.DefaultPath;
import fgroupindonesia.printscreen.engine.PrintScreen;
import java.io.File;

/**
 *
 * @author staff
 */
public class EngineStates {

    public static File logo_absolute_path;
    public static final String default_directory_path = DefaultPath.systemLocation;
    public static String directory_path = null;
    public static boolean copy_to_clipboard = true;
    public static boolean auto_cleanup = false;
    public static boolean always_ask_directory = false;
    public static boolean snipping_mode = true;
    public static boolean logo_visibility = true;
    public static boolean open_saved_directory = true;
    public static PrintScreen.PSImageFormat imageformat = PrintScreen.PSImageFormat.PNG;

}
