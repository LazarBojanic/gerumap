package rs.raf.gerumap.controller.editorChangeStateActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.CursorFactoryEnums;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.MindMapCursorFactory;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionAddTermState extends AbstractMapAction {


    public ActionAddTermState() {
        super("addStateIcon.png");
        putValue(NAME, "Add Term");
        putValue(SHORT_DESCRIPTION, "Click to add new terms");

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, (int) 'A');
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCurrentProjectView().getStateManager().setAddTermState();

        MindMapView mindMapView = (MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView();
        mindMapView.setCursor(MindMapCursorFactory.getCursor(CursorFactoryEnums.ADD));

        mindMapView.getSelectionModel().unselectCurrentSelection();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getCurrentProjectView());
    }
}
