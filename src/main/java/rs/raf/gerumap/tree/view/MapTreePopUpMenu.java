package rs.raf.gerumap.tree.view;

import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;

public class MapTreePopUpMenu extends JPopupMenu {

    private JMenu newMenu;

    public MapTreePopUpMenu() {

        newMenu = new JMenu(MainFrame.getInstance().getActionManager().getActionNew());
        newMenu.add(MainFrame.getInstance().getActionManager().getActionNewProject());
        newMenu.add(MainFrame.getInstance().getActionManager().getActionNewMindMap());
        newMenu.add(MainFrame.getInstance().getActionManager().getActionNewTerm());
        add(newMenu);
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getActionDelete());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getActionRename());
        pack();
    }
}
