package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.core.ApplicationFramework;
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

public class ActionLoadMindMapTemplate extends AbstractMapAction {

    public ActionLoadMindMapTemplate() {
        super("loadMindMapTemplate.png");
        putValue(NAME, "Load Mind Map Template");
        putValue(SHORT_DESCRIPTION, "Load Mind Map Template");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jFileChooser = new JFileChooser();

        File selectedFile = null;
        jFileChooser.setSelectedFile(workingDirectory.resolve("src/main/resources/savedTemplates/defaultMindMapTemplate.json").toFile());

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");

        jFileChooser.addChoosableFileFilter(filter);
        jFileChooser.setFileFilter(filter);

        if(jFileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){

            selectedFile = jFileChooser.getSelectedFile();

            if(!selectedFile.getName().contains("."))
                selectedFile = new File(selectedFile + ".json");

            ApplicationFramework.getInstance().getISerializer().loadMindMapTemplate(selectedFile);
        }
    }
}
