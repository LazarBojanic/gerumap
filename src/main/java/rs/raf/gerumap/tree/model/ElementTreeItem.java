package rs.raf.gerumap.tree.model;

import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import javax.swing.*;

public abstract class ElementTreeItem extends MapTreeItem {

    public ElementTreeItem(MapNode nodeModel) {
        super(nodeModel);
    }
}
