package rs.raf.gerumap.editorMindMap.editorState;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.controller.globalActions.ActionRename;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.menu.ContextMenu;
import rs.raf.gerumap.globalView.popUpPanes.RenameUpdateOnInputPopUpPane;
import rs.raf.gerumap.globalView.popUpPanes.editElementsPane.EditElementsPane;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;


public class SelectState implements IState{

    private Point startSelectionPoint;
    private Point endSelectionPoint;

    @Override
    public void mouseClickAction(Object event) {
        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) mouseEvent.getSource();

        ElementPainter elementPainter = mindMapView.getGraphicsAtLocation(IState.getScaledPoint(mouseEvent.getPoint(), mindMapView.getMindMap().getSavedZoom()));

        /*if(mouseEvent.getClickCount() == 2 && !mindMapView.getSelectionModel().getMultipleSelectionElements().isEmpty()){
            List<MapNode> modelList = new LinkedList<>();
            for(ElementPainter element : mindMapView.getSelectionModel().getMultipleSelectionElements()) {
                modelList.add(element.getModel());
            }
            new EditElementsPane(MainFrame.getInstance(), modelList, "Modify element", true, 190, 270);
            return;
        }
        else if (mouseEvent.getClickCount() == 2 && elementPainter != null){
            List<MapNode> modelList = new LinkedList<>();
            modelList.add(elementPainter.getModel());
            new EditElementsPane(MainFrame.getInstance(), modelList, "Modify element", true, 190, 270);
            return;
        }*/
        mindMapView.getSelectionModel().unselectCurrentSelection();
        mindMapView.getSelectionModel().setSingleSelectionElement(elementPainter);

        if(elementPainter != null){
            elementPainter.getModel().notifySubscribers(null, NotificationType.SET_PATH);
        }

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    @Override
    public void mouseDraggedAction(Object event) {
        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = ((MindMapView) mouseEvent.getSource());

        endSelectionPoint = IState.getScaledPoint(mouseEvent.getPoint(), mindMapView.getMindMap().getSavedZoom());

        mindMapView.getSelectionModel().unselectCurrentSelection();

        mindMapView.setLassoRectangle(new Rectangle2D.Double(
                Math.min(startSelectionPoint.x, endSelectionPoint.x),
                Math.min(startSelectionPoint.y, endSelectionPoint.y),
                Math.abs(startSelectionPoint.x - endSelectionPoint.x),
                Math.abs(startSelectionPoint.y - endSelectionPoint.y)
        ));

        mindMapView.selectElementsLasso();

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    @Override
    public void mousePressedAction(Object event) {
        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) mouseEvent.getSource();

        mindMapView.getSelectionModel().unselectCurrentSelection();
        startSelectionPoint = IState.getScaledPoint(mouseEvent.getPoint(), mindMapView.getMindMap().getSavedZoom());
    }

    @Override
    public void mouseReleasedAction(Object event) {
        startSelectionPoint = null;
        endSelectionPoint = null;

        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = ((MindMapView) mouseEvent.getSource());
        mindMapView.setLassoRectangle(null);

        System.out.println(mindMapView.getSelectionModel().getMultipleSelectionElements());

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    @Override
    public void mouseEnteredAction(Object event) {

    }

    @Override
    public void mouseExitedAction(Object event) {

    }

    @Override
    public void mouseMovedAction(Object event) {

    }
}
