package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.centralizedProjectView.selectionModel.SelectionModel;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.popUpPanes.editElementsPane.EditElementsPane;
import rs.raf.gerumap.model.repository.composite.MapNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ActionEditElementProperties extends AbstractMapAction {
    public ActionEditElementProperties() {
        super("editProperties.png");
        putValue(NAME, "editElementProperties");
        putValue(SHORT_DESCRIPTION, "Edit element properties");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, (int) 'T');

        ImageIcon imageIcon = getImage("editProperties.png");

        putValue(SMALL_ICON, imageIcon);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        SelectionModel selectionModel = ((MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView()).getSelectionModel();

        if(!selectionModel.getMultipleSelectionElements().isEmpty()){
            List<ElementPainter> elements = selectionModel.getMultipleSelectionElements();
            List<MapNode> modelList = new LinkedList<>();
            for(ElementPainter element : elements) {
                modelList.add(element.getModel());
            }
            new EditElementsPane(MainFrame.getInstance(), modelList, "Modify elements", true, 190, 270);
            return;
        }
        ElementPainter element = selectionModel.getSingleSelectionElement();
        if (element != null){
            List<MapNode> modelList = new LinkedList<>();
            modelList.add(element.getModel());
            new EditElementsPane(MainFrame.getInstance(), modelList, "Modify element", true, 190, 270);
        }
    }

    private ImageIcon getImage(String path){

        BufferedImage editPropertiesImage = null;
        try{
            editPropertiesImage = ImageIO.read(Objects.requireNonNull(this.getClass().getResource(path)));
        }
        catch(IOException e){

        }
        return new ImageIcon(new ImageIcon(editPropertiesImage).getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH));

    }
}