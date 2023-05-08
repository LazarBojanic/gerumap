package rs.raf.gerumap.tree.controller;

import rs.raf.gerumap.Main;
import rs.raf.gerumap.centralizedProjectView.ProjectView;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.*;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.view.MapTreeView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapTreeViewMouseListener implements MouseListener {
    private MapTreeItem mapTreeItem;
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2){

            MapNode mapNode = getModelOfCurrentlyOpeningTreeItem(e);

            ProjectView projectView = null;
            int nodeOrdinal = 0;

            if (mapNode instanceof Project){

                projectView = new ProjectView((Project) mapNode);
                nodeOrdinal = -1;

            } else if (mapNode instanceof MindMap){

                projectView = new ProjectView((Project) mapNode.getParent());
                nodeOrdinal = ((Project) mapNode.getParent()).getChildren().indexOf(mapNode);

            } else if (mapNode instanceof Term){

                projectView = new ProjectView((Project) mapNode.getParent().getParent());
                nodeOrdinal = ((Project) mapNode.getParent().getParent()).getChildren().indexOf(mapNode.getParent());

            } else if (mapNode instanceof Link){

                projectView = new ProjectView((Project) mapNode.getParent().getParent().getParent());
                nodeOrdinal = ((Project) mapNode.getParent().getParent().getParent()).getChildren().indexOf(mapNode.getParent().getParent());

            }

            MainFrame.getInstance().displayProject(projectView, nodeOrdinal);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private MapNode getModelOfCurrentlyOpeningTreeItem(MouseEvent e){

        MapTreeView mapTreeView = ((MapTreeView) e.getSource());
        mapTreeItem = (MapTreeItem) mapTreeView.getLastSelectedPathComponent();
        return mapTreeItem.getModel();

    }
}
