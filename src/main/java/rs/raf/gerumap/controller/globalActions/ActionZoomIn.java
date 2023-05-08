package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.ProjectView;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.observer.NotificationType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionZoomIn extends AbstractMapAction {

    public ActionZoomIn() {
        super("zoomInIcon.png");
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in this mind map");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = MainFrame.getInstance().getCurrentProjectView();

        if (projectView == null || projectView.getSelectedComponent() == null) return;

        MindMapView mindMapView = (MindMapView) ((JScrollPane) projectView.getSelectedComponent()).getViewport().getView();
        MindMap mindMap = mindMapView.getMindMap();

        if (mindMap.getZoomInCount() == 7) {
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.CANNOT_ZOOM_ANYMORE, null);
            return;
        }

        mindMap.setZoom(1.25);
        mindMap.setSavedZoom(mindMap.getSavedZoom() * 1.25);
        mindMap.setZoomInCount(mindMap.getZoomInCount()+1);

        mindMapView.getParentPane().getHorizontalScrollBar().setMaximum((int) (3000 * mindMap.getSavedZoom()));
        mindMapView.getParentPane().getVerticalScrollBar().setMaximum((int) (2000 * mindMap.getSavedZoom()));


        System.out.println(mindMap.getSavedZoom());

        mindMap.notifySubscribers(null, NotificationType.ZOOM);
    }
}
