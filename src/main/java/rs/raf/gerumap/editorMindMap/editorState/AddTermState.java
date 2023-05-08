package rs.raf.gerumap.editorMindMap.editorState;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.commandManagement.AddElementCommand;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.model.repository.factory.MapNodeFactoryManager;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.model.repository.implementation.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddTermState implements IState{
    @Override
    public void mouseClickAction(Object event) {
        MouseEvent e = ((MouseEvent) event);
        MindMapView mindMapView = (MindMapView) ((MouseEvent) event).getSource();
        MindMap mindMap = mindMapView.getMindMap();

        Term term = (Term) MapNodeFactoryManager.getMapNodeFactory(mindMap).createMapNode(mindMap);

        String name = "";

        while (name.equals("")) {

            name = JOptionPane.showInputDialog("Please enter the name of the term");

            if (name == null) return;

            if (name.equals(""))
                ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NAME_CANNOT_BE_EMPTY, term);
        }

        term.setName(name);

        Point point = IState.getScaledPoint(e.getPoint(), mindMap.getSavedZoom());

        int x = point.x - term.getEllipseDimension().width/2;
        int y = point.y - term.getEllipseDimension().height/2;

        term.setLocation(new Point(x, y));

        mindMap.getCommandManager().addCommand(new AddElementCommand(mindMap, term));

        SwingUtilities.updateComponentTreeUI(mindMapView);
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

    }
}
