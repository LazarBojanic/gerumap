package rs.raf.gerumap.tree.model;

import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.model.treeNodeFactory.SimpleTreeNodeFactory;

import javax.swing.*;

public class ProjectExplorerTreeItem extends MapTreeItem {

    public ProjectExplorerTreeItem(MapNode nodeModel) {
        super(nodeModel);
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

        switch (notificationType) {
            case ADD -> {
                addItem(SimpleTreeNodeFactory.getNode((MapNode) notification));
            }
            case DELETE -> deleteItem(notification);
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTreeView());
    }
}
