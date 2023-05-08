package rs.raf.gerumap.tree.model.abstraction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.observer.ISubscriber;
import rs.raf.gerumap.tree.model.treeNodeFactory.SimpleTreeNodeFactory;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public abstract class MapTreeItem extends DefaultMutableTreeNode implements ISubscriber {
    private MapNode model;
    public MapTreeItem(MapNode nodeModel){
        this.model = nodeModel;
        nodeModel.addSubscriber(this);
    }
    @Override
    public String toString() {
        return model.toString();
    }

    public void setName(String name) {
        this.model.setName(name);
    }

    protected void deleteItem(Object notification){
        //An item can be deleted only if memory address of its model matches the address of forwarded model
        for (TreeNode mapTreeItem : children){
            //notification is of type Map Node
            if (((MapTreeItem) mapTreeItem).getModel() == notification) {
               // ((DefaultTreeModel) MainFrame.getInstance().getMapTreeView().getModel()).removeNodeFromParent((MutableTreeNode) mapTreeItem);
                ((MapTreeItem) mapTreeItem.getParent()).remove((MutableTreeNode) mapTreeItem);
                ((MapTreeItem) mapTreeItem).finalizeMapTreeItem();
                break;

            }
        }
    }

    public void addItem(MutableTreeNode treeNode){

        add(treeNode);

        if (((MapTreeItem) treeNode).getModel() instanceof MapNodeComposite){
            for (MapNode mapNode : ((MapNodeComposite) ((MapTreeItem) treeNode).getModel()).getChildren()){
                ((MapTreeItem) treeNode).addItem(SimpleTreeNodeFactory.getNode(mapNode));
            }
        }

    }

    public void finalizeMapTreeItem(){
        model.removeSubscriber(this);
        model = null;
    }
}
