package rs.raf.gerumap.globalView.toolbars;

import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EditorToolbar extends JToolBar {

    public EditorToolbar() {

        initToolbar();
        initComponents();

    }

    private void initToolbar(){
        setPreferredSize(new Dimension(45, 400));
        setBackground(Color.PINK);
        setBorder(new LineBorder(Color.BLACK, 2, true));
        setOrientation(VERTICAL);
        setFloatable(false);

    }

    private void initComponents(){

        add(Box.createVerticalStrut(3));
        add(MainFrame.getInstance().getActionManager().getActionAddTermState()); //getComponent(1).getLocation().translate(4, 0);
        add(MainFrame.getInstance().getActionManager().getActionLinkElementsState()); //getComponent(2).getLocation().translate(4, 0);
        add(MainFrame.getInstance().getActionManager().getActionDeleteState());// getComponent(3).getLocation().translate(4, 0);
        add(MainFrame.getInstance().getActionManager().getActionSelectState()); //getComponent(4).getLocation().translate(4, 0);
        add(MainFrame.getInstance().getActionManager().getActionMoveState()); //getComponent(5).getLocation().translate(4, 0);
        add(MainFrame.getInstance().getActionManager().getActionEditElementProperties()); getComponent(6).getLocation().translate(4, 0);
        add(MainFrame.getInstance().getActionManager().getActionSetCenterTerm());
        add(Box.createHorizontalStrut(5));
    }
}
