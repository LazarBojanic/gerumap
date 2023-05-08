package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.ProjectView;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.observer.NotificationType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionZoomOut extends AbstractMapAction {

    public ActionZoomOut() {
        super("zoomOutIcon.png");
        putValue(NAME, "Zoom out");
        putValue(SHORT_DESCRIPTION, "Zoom out this mind map");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = MainFrame.getInstance().getCurrentProjectView();

        if (projectView == null || projectView.getSelectedComponent() == null) return;

        MindMapView mindMapView = (MindMapView) ((JScrollPane) projectView.getSelectedComponent()).getViewport().getView();
        MindMap mindMap = mindMapView.getMindMap();

        mindMap.setZoom(0.80);
        mindMap.setSavedZoom(mindMap.getSavedZoom() * 0.8);
        mindMap.setZoomInCount(mindMap.getZoomInCount()-1);

        mindMapView.getParentPane().getVerticalScrollBar().setMaximum((int) (2000 * mindMap.getSavedZoom()));
        mindMapView.getParentPane().getHorizontalScrollBar().setMaximum((int) (3000 * mindMap.getSavedZoom()));

        System.out.println(mindMap.getSavedZoom());

        mindMap.notifySubscribers(null, NotificationType.ZOOM);
    }
}
