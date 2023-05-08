package rs.raf.gerumap.globalView.frame;

import javax.swing.*;
import java.awt.*;

public class BasicDialog extends JDialog {
    public BasicDialog(Frame parent, String title, boolean modal, int sizeX, int sizeY){
        super(parent, title, modal);
        setSize(sizeX, sizeY);
        setLocationRelativeTo(null);
    }
}