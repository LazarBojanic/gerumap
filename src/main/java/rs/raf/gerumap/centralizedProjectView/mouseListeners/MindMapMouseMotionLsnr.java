package rs.raf.gerumap.centralizedProjectView.mouseListeners;

import rs.raf.gerumap.globalView.frame.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MindMapMouseMotionLsnr implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().getCurrentState().mouseDraggedAction(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().getCurrentState().mouseMovedAction(e);
    }
}
