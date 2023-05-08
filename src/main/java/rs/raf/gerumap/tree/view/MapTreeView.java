package rs.raf.gerumap.tree.view;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.tree.controller.MapTreeCellEditor;
import rs.raf.gerumap.tree.controller.MapTreeSelectionListener;
import rs.raf.gerumap.tree.controller.MapTreeViewMouseListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

@Getter
@Setter
public class MapTreeView extends JTree {

    private MapTreeCellRenderer ruTreeCellRenderer;
    private MapTreeCellEditor mapTreeCellEditor;
    private MapTreeSelectionListener mapTreeSelectionListener;
    private MapTreeViewMouseListener mapTreeViewMouseListener;
    private MapTreePopUpMenu treePopUpMenu;

    public MapTreeView(DefaultTreeModel defaultTreeModel){

        setModel(defaultTreeModel);
        initFields();
        initJTree();

    }

    public void expandSelectedNode(){
        expandPath(getSelectionPath());
    }

    private void initFields(){
        ruTreeCellRenderer = new MapTreeCellRenderer();
        mapTreeCellEditor = new MapTreeCellEditor(this, ruTreeCellRenderer);
        mapTreeSelectionListener = new MapTreeSelectionListener();
        mapTreeViewMouseListener = new MapTreeViewMouseListener();
        treePopUpMenu = new MapTreePopUpMenu();
    }

    private void initJTree(){


        setComponentPopupMenu(treePopUpMenu);
        setCellRenderer(ruTreeCellRenderer);
        setCellEditor(mapTreeCellEditor);
        addTreeSelectionListener(mapTreeSelectionListener);
        addMouseListener(mapTreeViewMouseListener);

        setEditable(true);

    }
}