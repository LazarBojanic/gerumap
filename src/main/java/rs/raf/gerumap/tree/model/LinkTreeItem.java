package rs.raf.gerumap.tree.model;

import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.NotificationType;

import javax.swing.*;
import javax.swing.tree.TreePath;

import java.util.Arrays;

import static rs.raf.gerumap.observer.NotificationType.*;

public class LinkTreeItem extends ElementTreeItem{

    public LinkTreeItem(MapNode nodeModel) {
        super(nodeModel);
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

        switch (notificationType) {
            case SET_PATH -> {
                System.out.printf(Arrays.toString(getPath()));
                MainFrame.getInstance().getMapTreeView().setSelectionPath(new TreePath(getPath()));
            }
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTreeView());
    }
}
