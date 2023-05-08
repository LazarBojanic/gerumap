package rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.net.URL;

public class MindMapCursorFactory {

    public static Cursor getCursor(CursorFactoryEnums name){

        switch (name){
            case DELETE -> {
                URL fileName = MindMapCursorFactory.class.getResource("scissorsCursorIcon.png");
                ImageIcon icon = new ImageIcon(fileName);
                return Toolkit.getDefaultToolkit().createCustomCursor(icon.getImage(), new Point(10, 10), "cursor");
            }
            case ADD -> {
                URL fileName = MindMapCursorFactory.class.getResource("addCursorIcon.png");
                ImageIcon icon = new ImageIcon(fileName);
                return Toolkit.getDefaultToolkit().createCustomCursor(icon.getImage(), new Point(10, 10), "cursor");
            }
            case LINK -> {
                URL fileName = MindMapCursorFactory.class.getResource("linkCursorIcon.png");
                ImageIcon icon = new ImageIcon(fileName);
                return Toolkit.getDefaultToolkit().createCustomCursor(icon.getImage(), new Point(10, 10), "cursor");
            }
            case SELECT -> {
                return Cursor.getDefaultCursor();
            }
            case MOVE -> {
                return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
            }
        }
        return Cursor.getDefaultCursor();
    }

}
