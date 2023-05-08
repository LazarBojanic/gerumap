package rs.raf.gerumap.globalView.menu;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;

@Getter
@Setter
public class Menu extends JMenuBar {

    private JMenu file;
    private JMenu newItem;
    private JMenuItem newProject;
    private JMenuItem newMindMap;
    private JMenuItem newElement;

    private JMenu edit;
    private JMenuItem delete;
    private JMenuItem rename;
    private JMenuItem setAuthor;
    private JMenu zoom;
    private JMenuItem zoomIn;
    private JMenuItem zoomOut;
    private JMenuItem undo;
    private JMenuItem redo;


    private JMenuItem saveProject;
    private JMenuItem loadProject;

    private JMenuItem exportMindMap;
    private JMenuItem saveMindMapTemplate;
    private JMenuItem loadMindMapTemplate;
    private JMenu help;
    private JMenuItem info;

    public Menu() {
        file = new JMenu("File");
        file.setMnemonic('F');

        newItem = new JMenu(MainFrame.getInstance().getActionManager().getActionNew());
        newItem.setMnemonic('N');
        newProject = new JMenuItem(MainFrame.getInstance().getActionManager().getActionNewProject());
        newMindMap = new JMenuItem(MainFrame.getInstance().getActionManager().getActionNewMindMap());
        newElement = new JMenuItem(MainFrame.getInstance().getActionManager().getActionNewTerm());

        edit = new JMenu("Edit");
        edit.setMnemonic('E');
        delete = new JMenuItem(MainFrame.getInstance().getActionManager().getActionDelete());
        rename = new JMenuItem(MainFrame.getInstance().getActionManager().getActionRename());
        setAuthor = new JMenuItem(MainFrame.getInstance().getActionManager().getActionSetAuthor());
        zoom = new JMenu("Zoom");
        zoom.setMnemonic('Z');
        zoomIn = new JMenuItem(MainFrame.getInstance().getActionManager().getActionZoomIn());
        zoomOut = new JMenuItem(MainFrame.getInstance().getActionManager().getActionZoomOut());
        undo = new JMenuItem(MainFrame.getInstance().getActionManager().getActionUndo());
        redo = new JMenuItem(MainFrame.getInstance().getActionManager().getActionRedo());

        saveProject = new JMenuItem(MainFrame.getInstance().getActionManager().getActionSaveProject());
        loadProject = new JMenuItem(MainFrame.getInstance().getActionManager().getActionLoadProject());

        exportMindMap = new JMenuItem(MainFrame.getInstance().getActionManager().getActionExportMindMap());
        saveMindMapTemplate = new JMenuItem(MainFrame.getInstance().getActionManager().getActionSaveMindMapTemplate());
        loadMindMapTemplate = new JMenuItem(MainFrame.getInstance().getActionManager().getActionLoadMindMapTemplate());


        help = new JMenu("Help");
        info = new JMenuItem(MainFrame.getInstance().getActionManager().getActionInfo());

        file.add(newItem);
        newItem.add(newProject);
        newItem.addSeparator();
        newItem.add(newMindMap);
        newItem.addSeparator();
        newItem.add(newElement);

        edit.add(undo);
        edit.add(redo);
        edit.addSeparator();
        edit.add(delete);
        edit.addSeparator();
        edit.add(rename);
        edit.add(setAuthor);
        edit.add(zoom);
        zoom.add(zoomIn);
        zoom.add(zoomOut);

        file.add(saveProject);
        file.add(loadProject);
        file.add(exportMindMap);

        file.add(saveMindMapTemplate);
        file.add(loadMindMapTemplate);

        help.add(info);

        add(file);
        add(edit);
        add(help);
    }
}