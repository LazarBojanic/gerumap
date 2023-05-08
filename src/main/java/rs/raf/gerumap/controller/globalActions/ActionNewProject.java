package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.gui.SwingGui;
import rs.raf.gerumap.model.repository.factory.MapNodeFactory;
import rs.raf.gerumap.model.repository.factory.MapNodeFactoryManager;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionNewProject extends AbstractMapAction {
    public ActionNewProject() {
        super("newProject.png");
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "Create new project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectExplorer projectExplorer = ApplicationFramework.getInstance().getIMapRepository().getProjectExplorer();

        String author = JOptionPane.showInputDialog("Please enter the name of the author");

        if (author == null){

            return;

        } else if (author.equals("")){

            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NAME_CANNOT_BE_EMPTY, projectExplorer);
            actionPerformed(e);

        } else {

            MapNodeFactory mapNodeFactory = MapNodeFactoryManager.getMapNodeFactory(projectExplorer);
            ((Project) mapNodeFactory.getMapNode(projectExplorer)).setAuthor(author);

        }

        ((SwingGui) ApplicationFramework.getInstance().getIGui()).getMainFrame().getMapTreeView().expandSelectedNode();
    }
}