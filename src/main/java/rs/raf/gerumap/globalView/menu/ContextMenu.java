package rs.raf.gerumap.globalView.menu;

import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;

public class ContextMenu extends JPopupMenu{
    public ContextMenu() {
        //System.out.printf(String.valueOf(getParent()));

        add(MainFrame.getInstance().getActionManager().getActionRename());
        pack();
    }
}