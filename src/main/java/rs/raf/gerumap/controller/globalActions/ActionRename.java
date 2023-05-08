package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.popUpPanes.RenameUpdateOnInputPopUpPane;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import javax.swing.*;
import java.awt.event.*;

public class ActionRename extends AbstractMapAction {

    public ActionRename() {
        super("renameIcon.png");
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename selected node");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.ALT_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeItem mapNodeTreeItem = (MapTreeItem) MainFrame.getInstance().getMapTreeView().getLastSelectedPathComponent();

        if (mapNodeTreeItem == null){
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NO_NODE_SELECTED, null);
            return;
        }

        RenameUpdateOnInputPopUpPane renameUpdateOnInputPopUpPane = new RenameUpdateOnInputPopUpPane(MainFrame.getInstance(), "Rename", true, 200, 200, NotificationType.NAME_CHANGE);

        MapNode mapNode = mapNodeTreeItem.getModel();
        String name = mapNode.getName();
        renameUpdateOnInputPopUpPane.setOriginalName(name);

        renameUpdateOnInputPopUpPane.getTextField().setText(name);
        renameUpdateOnInputPopUpPane.getTextField().setEditable(true);

        renameUpdateOnInputPopUpPane.setVisible(true);
    }

}
