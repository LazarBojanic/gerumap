package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.gui.SwingGui;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.factory.MapNodeFactory;
import rs.raf.gerumap.model.repository.factory.MapNodeFactoryManager;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
import rs.raf.gerumap.tree.view.MapTreeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionNewTerm extends AbstractMapAction {
    public ActionNewTerm() {
        super("/rs/raf/gerumap/tree/view/elementIcon.png");
        putValue(NAME, "New Term");
        putValue(SHORT_DESCRIPTION, "Create new element");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MapTreeView mapTreeView = ((SwingGui) ApplicationFramework.getInstance().getIGui()).getMainFrame().getMapTreeView();
        MapTreeItem mindMapTreeItem = ((MapTreeItem) mapTreeView.getLastSelectedPathComponent());

        if (mindMapTreeItem == null){
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NO_NODE_SELECTED, null);
            return;
        } else if (!(mindMapTreeItem.getModel() instanceof MindMap)){
            String[] str = new String[]{"Term", mindMapTreeItem.getModel().toString(), "MindMap"};
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.WRONG_NODE_SELECTED, str);
            return;
        }

        MapNodeFactory mapNodeFactory = MapNodeFactoryManager.getMapNodeFactory(mindMapTreeItem.getModel());
        mapNodeFactory.getMapNode((MapNodeComposite) mindMapTreeItem.getModel());

        ((SwingGui) ApplicationFramework.getInstance().getIGui()).getMainFrame().getMapTreeView().expandSelectedNode();
    }
}
