package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.gui.SwingGui;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.factory.MapNodeFactory;
import rs.raf.gerumap.model.repository.factory.MapNodeFactoryManager;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.view.MapTreeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionNewMindMap extends AbstractMapAction {
    public ActionNewMindMap() {
        super("newMindMap.png");
        putValue(NAME, "New Mind Map");
        putValue(SHORT_DESCRIPTION, "Create new mind map");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MapTreeView mapTreeView = ((SwingGui) ApplicationFramework.getInstance().getIGui()).getMainFrame().getMapTreeView();
        MapTreeItem projectTreeItem = ((MapTreeItem) mapTreeView.getLastSelectedPathComponent());

        if (projectTreeItem == null){

            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NO_NODE_SELECTED, null);
            return;

        } else if (!(projectTreeItem.getModel() instanceof Project)){

            String[] str = new String[]{"MindMap", projectTreeItem.getModel().toString(), "Project"};
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.WRONG_NODE_SELECTED, str);
            return;

        }

        MapNodeFactory mapNodeFactory = MapNodeFactoryManager.getMapNodeFactory(projectTreeItem.getModel());
        mapNodeFactory.getMapNode((MapNodeComposite) projectTreeItem.getModel());

        ((SwingGui) ApplicationFramework.getInstance().getIGui()).getMainFrame().getMapTreeView().expandSelectedNode();
    }
}