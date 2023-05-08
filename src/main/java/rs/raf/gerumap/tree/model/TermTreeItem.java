package rs.raf.gerumap.tree.model;

import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.treeNodeFactory.SimpleTreeNodeFactory;

import javax.swing.*;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.Arrays;

public class TermTreeItem extends ElementTreeItem{

    public TermTreeItem(MapNode nodeModel) {
        super(nodeModel);
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

        switch (notificationType) {
            case ADD -> addItem(SimpleTreeNodeFactory.getNode(((MapNode) notification)));

            case DELETE -> {
                deleteItem(notification);
            }
            case SET_PATH -> {
                System.out.println(Arrays.toString(getPath()));
                MainFrame.getInstance().getMapTreeView().setSelectionPath(new TreePath(getPath()));
            }
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTreeView());
    }
}
