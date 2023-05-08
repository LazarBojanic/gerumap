package rs.raf.gerumap.editorMindMap.editorState;

import rs.raf.gerumap.globalView.frame.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface IState {
    static Point getScaledPoint(Point point, double zoom){
        return new Point((int) (point.x / zoom), (int) (point.y / zoom));
    }
    void mouseClickAction(Object event);

    void mouseDraggedAction(Object event);

    void mousePressedAction(Object event);

    void mouseReleasedAction(Object event);

    void mouseEnteredAction(Object event);

    void mouseExitedAction(Object event);

    void mouseMovedAction(Object event
    );

}
