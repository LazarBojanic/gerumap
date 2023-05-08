package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.popUpPanes.RenameUpdateOnInputPopUpPane;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import javax.swing.*;
import java.awt.event.*;

public class ActionSetAuthor extends AbstractMapAction {

    public ActionSetAuthor() {
        super("setAuthorIcon.png");
        putValue(NAME, "Set author");
        putValue(SHORT_DESCRIPTION, "Set author fot selected node");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    }

    public void actionPerformed(ActionEvent e) {

        MapTreeItem mapTreeItem = ((MapTreeItem) MainFrame.getInstance().getMapTreeView().getLastSelectedPathComponent());

        if (mapTreeItem == null || !(mapTreeItem.getModel() instanceof Project)){
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.CANNOT_CHANGE_AUTHOR, null);
            return;
        }

        RenameUpdateOnInputPopUpPane renameUpdateOnInputPopUpPane = new RenameUpdateOnInputPopUpPane(MainFrame.getInstance(), "Rename", true, 200, 200, NotificationType.AUTHOR_CHANGE);

        String name = ((Project) mapTreeItem.getModel()).getAuthor();
        renameUpdateOnInputPopUpPane.setOriginalName(name);

        renameUpdateOnInputPopUpPane.getTextField().setText(name);
        renameUpdateOnInputPopUpPane.getTextField().setEditable(true);

        renameUpdateOnInputPopUpPane.setVisible(true);
    }

}
