package rs.raf.gerumap.controller.editorChangeStateActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.CursorFactoryEnums;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.MindMapCursorFactory;
import rs.raf.gerumap.commandManagement.DeleteElementCommand;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ActionDeleteState extends AbstractMapAction {

    public ActionDeleteState() {
        super("deleteIcon.png");
        putValue(NAME, "Delete elements");
        putValue(SHORT_DESCRIPTION, "Delete elements from mind map");

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, (int) 'D');
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getCurrentProjectView().getStateManager().setDeleteState();

        MindMapView mindMapView = (MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView();
        mindMapView.setCursor(MindMapCursorFactory.getCursor(CursorFactoryEnums.DELETE));

        if (!mindMapView.getSelectionModel().getMultipleSelectionElements().isEmpty()){

//            for (ElementPainter elementPainter1 : mindMapView.getSelectionModel().getMultipleSelectionElements()){
//                ((MapNodeComposite) elementPainter1.getModel().getParent()).deleteChild(elementPainter1.getModel());
//            }

            mindMapView.getMindMap().getCommandManager().addCommand(new DeleteElementCommand(mindMapView));

            mindMapView.getSelectionModel().unselectCurrentSelection();
            MainFrame.getInstance().getCurrentProjectView().getStateManager().setSelectState();
            mindMapView.setCursor(MindMapCursorFactory.getCursor(CursorFactoryEnums.SELECT));
        }
    }
}
