package rs.raf.gerumap.tree;

import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;
import rs.raf.gerumap.tree.model.ProjectExplorerTreeItem;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.view.MapTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

import java.util.Random;

public class MapTreeImplementation implements IMapTree {
    private MapTreeView mapTreeView;
    private DefaultTreeModel defaultTreeModel;
    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        ProjectExplorerTreeItem projectExplorerTreeItem = new ProjectExplorerTreeItem(projectExplorer);
        defaultTreeModel = new DefaultTreeModel(projectExplorerTreeItem);
        mapTreeView = new MapTreeView(defaultTreeModel);
        return mapTreeView;
    }

    @Override
    public void addChild(MapTreeItem parent) {
        if (!(parent.getModel() instanceof MapNodeComposite)){
            return;
        }
        MapNode child = createChild(parent.getModel());
       // parent.add(new MapTreeItem(child));
        ((MapNodeComposite) parent.getModel()).addChild(child);
        mapTreeView.expandPath(mapTreeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(mapTreeView);
    }
    private MapNode createChild(MapNode parent) {
        if (parent instanceof ProjectExplorer){
            return new Project("Project" + new Random().nextInt(100), parent);
        }
        return null;
    }
    @Override
    public MapTreeItem getSelectedNode() {
        return null;
    }
}