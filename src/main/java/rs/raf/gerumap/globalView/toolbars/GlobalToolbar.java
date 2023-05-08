package rs.raf.gerumap.globalView.toolbars;

import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GlobalToolbar extends JToolBar {

    private JTextField editNameTextField;

    private JButton zoomIn;
    private JButton zoomOut;

    public GlobalToolbar() {
        initToolbar();
        initComponents();
    }

    private void initToolbar(){
        setPreferredSize(new Dimension(600, 45));
        setBackground(Color.PINK);
        setBorder(new LineBorder(Color.BLACK, 2, true));
    }

    private void initComponents(){
        add(Box.createHorizontalStrut(3));
        add(MainFrame.getInstance().getActionManager().getActionNew());
        add(MainFrame.getInstance().getActionManager().getActionDelete());
        add(MainFrame.getInstance().getActionManager().getActionRename());
        add(MainFrame.getInstance().getActionManager().getActionSetAuthor());
        zoomIn = add(MainFrame.getInstance().getActionManager().getActionZoomIn());
        zoomOut = add(MainFrame.getInstance().getActionManager().getActionZoomOut());
        add(MainFrame.getInstance().getActionManager().getActionUndo());
        add(MainFrame.getInstance().getActionManager().getActionRedo());



        initEditNameTextField();
    }

    private void initEditNameTextField(){
        editNameTextField = new JTextField();
        editNameTextField.setMaximumSize(new Dimension(150, 32));
        editNameTextField.setToolTipText("edit node name");

        add(editNameTextField);
    }
}
