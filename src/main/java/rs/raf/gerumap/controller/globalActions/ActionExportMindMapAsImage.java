package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.implementation.MindMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ActionExportMindMapAsImage extends AbstractMapAction {

    public ActionExportMindMapAsImage() {
        super("exportMindMap.png");
        putValue(NAME, "Export Mind Map Image");
        putValue(SHORT_DESCRIPTION, "Export Mind Map as Image");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MindMapView mindMapView = ((MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView());
        MindMap mindMap = mindMapView.getMindMap();

        double savedZoom = mindMap.getSavedZoom();
        mindMapView.getAffineTransform().scale(1/savedZoom, 1/savedZoom);

        int width = 3000;
        int height = 2000;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedImage.createGraphics();
        ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView().paint(graphics2D);

        graphics2D.dispose();

        try {
            ImageIO.write(bufferedImage, "png", new File("export/mindMap.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        mindMapView.getAffineTransform().scale(savedZoom, savedZoom);
    }
}