package rs.raf.gerumap.controller.managementAndAbstraction;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractMapAction extends AbstractAction {
    public AbstractMapAction(String fileName) {
        putValue(SMALL_ICON, loadIcon(fileName));
    }
    public Icon loadIcon(String fileName){

        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }
}