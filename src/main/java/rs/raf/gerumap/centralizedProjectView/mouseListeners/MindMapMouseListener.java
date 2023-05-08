package rs.raf.gerumap.centralizedProjectView.mouseListeners;

import rs.raf.gerumap.globalView.frame.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MindMapMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().getCurrentState().mouseClickAction(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().getCurrentState().mousePressedAction(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().getCurrentState().mouseReleasedAction(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
