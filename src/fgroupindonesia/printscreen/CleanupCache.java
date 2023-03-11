package fgroupindonesia.printscreen;

import java.io.File;
import java.util.ArrayList;

/**
 * System : JavaPrintScreen
 * Description : 
 * This file will help us to clear every cache 
 * once the screenshot image was taken.
 * 
 * @author fgroupindonesia
 */
public class CleanupCache {

    ArrayList<String> exceptFile = new ArrayList<String>();

    public void exceptFile(String filename) {
        if(!exceptFile.contains(filename))
        exceptFile.add(filename);
    }

    public void cleanAll() {
        System.out.println("Cleanup cache is working...");
        File systemDir = new File(DefaultPath.systemLocation);

        for (File f : systemDir.listFiles()) {
            System.out.println("found " + f.getName());
            if (!exceptFile.contains(f.getName())) {
                f.delete();
                System.out.println(f.getName() + "- deleted." );
            }
        }
    }
}
