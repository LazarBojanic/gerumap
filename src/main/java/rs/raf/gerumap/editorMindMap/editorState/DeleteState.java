package rs.raf.gerumap.editorMindMap.editorState;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.commandManagement.DeleteElementCommand;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.MindMap;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class DeleteState implements IState{

    @Override
    public void mouseClickAction(Object event) {
        MouseEvent e = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) ((MouseEvent) event).getSource();

        ElementPainter elementPainter = mindMapView.getGraphicsAtLocation(IState.getScaledPoint(e.getPoint(), mindMapView.getMindMap().getSavedZoom()));

        if (elementPainter != null){
            mindMapView.getMindMap().getCommandManager().addCommand(new DeleteElementCommand((MapNodeComposite) elementPainter.getModel().getParent(), elementPainter.getModel()));
        }
    }

    @Override
    public void mouseDraggedAction(Object event) {

    }

    @Override
    public void mousePressedAction(Object event) {

    }

    @Override
    public void mouseReleasedAction(Object event) {

    }

    @Override
    public void mouseEnteredAction(Object event) {

    }

    @Override
    public void mouseExitedAction(Object event) {

    }

    @Override
    public void mouseMovedAction(Object event) {

        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) mouseEvent.getSource();

        mindMapView.getSelectionModel().setSingleSelectionElement(mindMapView.getGraphicsAtLocation(IState.getScaledPoint(mouseEvent.getPoint(), mindMapView.getMindMap().getSavedZoom())));

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }
}
