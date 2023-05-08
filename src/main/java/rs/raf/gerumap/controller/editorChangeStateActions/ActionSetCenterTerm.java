package rs.raf.gerumap.controller.editorChangeStateActions;

import lombok.Getter;
import rs.raf.gerumap.Main;
import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.model.repository.implementation.Term;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActionSetCenterTerm extends AbstractMapAction {


    public ActionSetCenterTerm() {
        super("centerTerm.png");
        putValue(NAME, "Set Center Term");
        putValue(SHORT_DESCRIPTION, "Set Center Term");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<MapNode> linkedTermList = new ArrayList<>();
        double phi = 0;
        double deltaPhi = 30;
        int linkLengthX = 25;
        int linkLengthY = 25;
        int thickness = 15;
        MindMapView mindMapView = (MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView();
        MindMap currentMindMap = mindMapView.getMindMap();

        MapNode mapNode = mindMapView.getSelectionModel().getSingleSelectionElement().getModel();
        Term currTerm;

        if (mapNode instanceof Term term)
            currTerm = ((Term) mapNode);
        else return;

        term.centralize(35);

        setChildrenLocation(((Term)mapNode), Math.PI*2, Math.PI, 0);

        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    private void setChildrenLocation(Term parent, double spanAngle, double middleAngle, int increment){

        List<Term> destinationChildren = getDestinationTerms(parent);

        int n = destinationChildren.size();
        double phi0 = middleAngle - spanAngle/2;//Math.PI/2;
        double deltaPhi = spanAngle/(n + increment);//Math.PI*2/n;

        if (n == 1) {
            deltaPhi = spanAngle;
            phi0 = middleAngle;
        }

        int radius = 170;

        for (int i = 0; i < n; i++){

            Term currTerm = destinationChildren.get(i);

            Point point = new Point();

            int x = (int) (parent.getLocation().x + parent.getEllipseDimension().width/2 + Math.cos(phi0 + deltaPhi*i)*radius);
            int y = (int) (parent.getLocation().y + parent.getEllipseDimension().height/2 - Math.sin(phi0 + deltaPhi*i)*radius);

            point.setLocation(x - currTerm.getEllipseDimension().width/2, y - currTerm.getEllipseDimension().height/2);

            currTerm.setLocation(point);
            currTerm.decentralize();

            setChildrenLocation(currTerm, deltaPhi, phi0 + deltaPhi*i, -1);

        }

    }

    private List<Term> getDestinationTerms(Term parent){

        List<MapNode> list = parent.getChildren().stream().filter(e2->{
            if (((Link) e2).getSourceTerm() == parent) return true;
            else return false;
        }).collect(Collectors.toList());

        List<Term> destinationTerms = new ArrayList<>();
        for (MapNode mapNode : list){
            destinationTerms.add(((Link) mapNode).getDestinationTerm());
        }

        return destinationTerms;
    }
}