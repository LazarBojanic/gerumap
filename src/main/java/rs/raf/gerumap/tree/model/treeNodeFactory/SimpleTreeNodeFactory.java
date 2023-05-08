package rs.raf.gerumap.tree.model.treeNodeFactory;

import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.tree.model.*;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import java.util.Map;

public class SimpleTreeNodeFactory {

    public static MapTreeItem getNode(MapNode mapNode){

        return switch (mapNode.getClass().getSimpleName()) {
            case "Project" -> new ProjectTreeItem(mapNode);
            case "MindMap" -> new MindMapTreeItem(mapNode);
            case "Term" -> new TermTreeItem(mapNode);
            case "Link" -> new LinkTreeItem(mapNode);
            default -> null;
        };
    }

}
