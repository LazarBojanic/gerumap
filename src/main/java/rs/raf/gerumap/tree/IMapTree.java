package rs.raf.gerumap.tree;

import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.view.MapTreeView;

public interface IMapTree {
    MapTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(MapTreeItem parent);
    MapTreeItem getSelectedNode();
}