package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.gui.SwingGui;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.view.MapTreeView;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionDelete extends AbstractMapAction {
    public ActionDelete() {
        super("deleteIcon.png");
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete selected item");

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, (int) 'D');
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeView mapTreeView = ((SwingGui) ApplicationFramework.getInstance().getIGui()).getMainFrame().getMapTreeView();
        MapTreeItem mapTreeItem = ((MapTreeItem) mapTreeView.getLastSelectedPathComponent());

        if (mapTreeItem == null){

            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NO_NODE_SELECTED, null);

        } else if (mapTreeItem.getModel() instanceof ProjectExplorer) {

            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.CANNOT_REMOVE_NODE, mapTreeItem.getModel());

        } else {

            MapNode mapNode = mapTreeItem.getModel();
            MapNodeComposite mapNodeComposite = ((MapNodeComposite) mapNode.getParent());  //Map Node that is to be deleted must have a parent, so it's safe to cast
            mapNodeComposite.deleteChild(mapNode);

            //parent node of deleted node becomes the currently selected node in a tree
            TreePath path = mapTreeView.getSelectionPath();
            mapTreeView.setSelectionPath(path.getParentPath());

        }
    }
}