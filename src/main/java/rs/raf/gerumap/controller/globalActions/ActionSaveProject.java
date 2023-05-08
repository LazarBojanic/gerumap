package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static rs.raf.gerumap.core.ApplicationFramework.workingDirectory;

public class ActionSaveProject extends AbstractMapAction {
    public ActionSaveProject() {
        super("save.png");
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save Project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (MainFrame.getInstance().getCurrentProjectView() == null) {
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NO_NODE_SELECTED, null);
            return;
        }

        JFileChooser jFileChooser = new JFileChooser();
        File selectedFile = null;
        jFileChooser.setSelectedFile(workingDirectory.resolve("src/main/resources/savedProjects/defaultProject.json").toFile());

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");

        jFileChooser.addChoosableFileFilter(filter);
        jFileChooser.setFileFilter(filter);

        if(jFileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){

            selectedFile = jFileChooser.getSelectedFile();

            if(!selectedFile.getName().contains("."))
                selectedFile = new File(selectedFile + ".json");

            ApplicationFramework.getInstance().getISerializer().saveProject(selectedFile);
        }
    }
}