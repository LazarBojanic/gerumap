package rs.raf.gerumap.tree.controller;

import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
//        TreePath path = e.getPath();
//        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
//        System.out.println("Selected node: " + treeItemSelected.getModel().getName());
//        System.out.println("Path: " + e.getPath());
    }
}
