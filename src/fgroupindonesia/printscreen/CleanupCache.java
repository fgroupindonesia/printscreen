package fgroupindonesia.printscreen;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author staff
 */
public class CleanupCache {

    ArrayList<String> exceptFile = new ArrayList<String>();

    public void exceptFile(String filename) {
        exceptFile.add(filename);
    }

    public void cleanAll() {
        File systemDir = new File(DefaultPath.systemLocation);

        for (File f : systemDir.listFiles()) {
            if (!exceptFile.contains(f.getName())) {
                f.delete();
            }
        }
    }
}
