package rs.raf.gerumap.tree.controller;

import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.popUpPanes.WarningPopUpPane;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;
    private JTextField edit = null;
    public MapTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }
    @Override
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);

        clickedOn = arg1;
        edit = new JTextField(arg1.toString());

        edit.addActionListener(this);

        return edit;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() instanceof JTextField) {

            MapNode mapNode = ((MapTreeItem)clickedOn).getModel();

            if (!(mapNode instanceof ProjectExplorer) && ((MapNodeComposite) mapNode.getParent()).containsName(e.getActionCommand(), mapNode)){

                String[] str = new String[]{mapNode.getParent().getName(), mapNode.getClass().getSimpleName(), e.getActionCommand()};
                ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.CONTAINING_SAME_NAME, str);


                if (WarningPopUpPane.warningPaneState == 2){
                    return;
                }

            } else if (e.getActionCommand().equals("")){
                ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NAME_CANNOT_BE_EMPTY, null);
                return;
            } //else if (mapNode instanceof Link) return;

            mapNode.setName(e.getActionCommand());
            mapNode.notifySubscribers(e.getActionCommand(), NotificationType.NAME_CHANGE);

        }
    }
}
