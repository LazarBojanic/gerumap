package rs.raf.gerumap.editorMindMap.editorState;


import rs.raf.gerumap.centralizedProjectView.LinkPainter;
import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.TermPainter;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.commandManagement.AddElementCommand;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.AlreadyLinkedMessage;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.model.repository.implementation.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkElementsState implements IState{
    @Override
    public void mouseClickAction(Object event) {

    }

    @Override
    public void mouseDraggedAction(Object event) {

        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) mouseEvent.getSource();
        MindMap mindMap = mindMapView.getMindMap();

        ElementPainter elementPainter = mindMapView.getGraphicsAtLocation(IState.getScaledPoint(mouseEvent.getPoint(), mindMap.getSavedZoom()));

        if(mindMapView.getSelectionModel().getSingleSelectionElement() != null &&
                mindMapView.getSelectionModel().getSingleSelectionElement() != elementPainter &&
                !(elementPainter instanceof LinkPainter)
        ){
            mindMapView.getSelectionModel().setSecondarySelectionElement(elementPainter);
        }

        if (mindMapView.getSelectionModel().getSingleSelectionElement() != null) {
            Point point = new Point(
                    (int) (((Term) mindMapView.getSelectionModel().getSingleSelectionElement().getModel()).getLocation().getX() +
                            ((Term) mindMapView.getSelectionModel().getSingleSelectionElement().getModel()).getEllipseDimension().width / 2),
                    (int) (((Term) mindMapView.getSelectionModel().getSingleSelectionElement().getModel()).getLocation().getY() +
                            ((Term) mindMapView.getSelectionModel().getSingleSelectionElement().getModel()).getEllipseDimension().height / 2)
            );

            mindMapView.setTemporaryLink(new Line2D.Double(point, IState.getScaledPoint(mouseEvent.getPoint(), mindMapView.getMindMap().getSavedZoom())));
        }

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    @Override
    public void mousePressedAction(Object event) {

        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) mouseEvent.getSource();
        MindMap mindMap = mindMapView.getMindMap();

        ElementPainter elementPainter = mindMapView.getGraphicsAtLocation(IState.getScaledPoint(mouseEvent.getPoint(), mindMap.getSavedZoom()));

        if (!(elementPainter instanceof LinkPainter))
            mindMapView.getSelectionModel().setSingleSelectionElement(elementPainter);

        mindMapView.setTemporaryLink(new Line2D.Double(0,0,0,0));

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    @Override
    public void mouseReleasedAction(Object event) {

        MouseEvent mouseEvent = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) mouseEvent.getSource();
        MindMap mindMap = mindMapView.getMindMap();

        if (mindMapView.getSelectionModel().getSingleSelectionElement() != null && mindMapView.getSelectionModel().getSecondarySelectionElement() != null )
            setNewLink(mindMapView);

        mindMapView.getSelectionModel().setSingleSelectionElement(null);
        mindMapView.getSelectionModel().setSecondarySelectionElement(null);

        mindMapView.setTemporaryLink(null);

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

    private void setNewLink(MindMapView mindMapView){

        Term source = (Term) mindMapView.getSelectionModel().getSingleSelectionElement().getModel();
        Term destination = (Term) mindMapView.getSelectionModel().getSecondarySelectionElement().getModel();

        List<MapNode> list = ((MapNodeComposite) mindMapView.getSelectionModel().getSingleSelectionElement().getModel()).getChildren();
        List<Term> terms = new ArrayList<>(list.stream().map(e -> ((Link) e).getDestinationTerm()).toList());
        terms.addAll(list.stream().map(e -> ((Link) e).getSourceTerm()).toList());

        if (terms.contains(destination)){
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.ALREADY_LINKED, new Term[]{source, destination});
            return;
        }


        Link link = new Link(source + " with " + destination, source, destination);

        mindMapView.getMindMap().getCommandManager().addCommand(new AddElementCommand(source, link));
    }
}


