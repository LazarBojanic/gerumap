package rs.raf.gerumap.controller.editorChangeStateActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.CursorFactoryEnums;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.MindMapCursorFactory;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionMoveState extends AbstractMapAction {

    public ActionMoveState() {
        super("moveStateIcon.png");
        putValue(NAME, "Move terms");
        putValue(SHORT_DESCRIPTION, "Click to move terms");

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, (int) 'M');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().setMoveState();

        MindMapView mindMapView = (MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView();
        mindMapView.setCursor(MindMapCursorFactory.getCursor(CursorFactoryEnums.MOVE));
    }
}
