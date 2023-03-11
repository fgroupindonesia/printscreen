
package fgroupindonesia.printscreen;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/*
 * System : JavaPrintScreen
 * Description : 
 * This file is used to enable the copy to clipboard features
 *
 * @author fgroupindonesia
 *
 */

public class TransferableImage implements Transferable {
    Image i;
    
    public TransferableImage(Image bi){
        this.i = bi;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
       DataFlavor[] flavors = new DataFlavor[1];
       flavors[0] = DataFlavor.imageFlavor;
       return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        DataFlavor [] fla = this.getTransferDataFlavors();
        for(int x =0 ; x < fla.length; x++){
            if(flavor.equals(fla[x])){
                return true;
            }
        }
    
    return false;
    }

    @Override
    public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
       if(df.equals(DataFlavor.imageFlavor) && this.i != null){
           return i;
       }
       
       return null;
    }
    
}
