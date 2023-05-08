package rs.raf.gerumap.commandManagement;

import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.commandManagement.abstraction.AbstractCommand;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.Term;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DeleteElementCommand extends AbstractCommand {

    private MapNodeComposite parent;
    private MapNode child;
    private ArrayList<MapNode> deletedLinks;
    private MindMapView mindMapDeleteSelection;
    private List<MapNode> selectionElements;
    private HashMap<MapNode, ArrayList<MapNode>> unselectedDeletedLinks;

    public DeleteElementCommand(MapNodeComposite parent, MapNode child) {
        this.parent = parent;
        this.child = child;
    }

    public DeleteElementCommand(MindMapView mindMapView) {
        this.mindMapDeleteSelection = mindMapView;
        this.selectionElements = new ArrayList<>();
        this.unselectedDeletedLinks = new HashMap<>();

        for (ElementPainter elementPainter1 : mindMapDeleteSelection.getSelectionModel().getMultipleSelectionElements()){
            selectionElements.add(elementPainter1.getModel());
        }
    }

    @Override
    public void doCommand() {

        if (mindMapDeleteSelection != null) {

            for (MapNode mapNode : selectionElements) {
                if(mapNode instanceof Term)
                    unselectedDeletedLinks.put(mapNode, new ArrayList<>(((MapNodeComposite) mapNode).getChildren()));
                ((MapNodeComposite) mapNode.getParent()).deleteChild(mapNode);
            }

            mindMapDeleteSelection.getSelectionModel().unselectCurrentSelection();

        } else {

            if (child instanceof Term)
                deletedLinks = new ArrayList<>(((Term) child).getChildren());

            parent.deleteChild(child);
        }
    }

    @Override
    public void undoCommand() {

        if (mindMapDeleteSelection != null){

            for (MapNode mapNode : selectionElements) {

                if (mapNode instanceof Link && !((MapNodeComposite) mapNode.getParent()).getChildren().contains(mapNode)) {
                    ((MapNodeComposite) mapNode.getParent()).addChild(mapNode);
                }

                if (mapNode instanceof Term) {

                    ((MapNodeComposite) mapNode.getParent()).addChild(mapNode);

                    for (MapNode link : unselectedDeletedLinks.get(mapNode))
                        if (!((Term) mapNode).getChildren().contains(link)) {
                            ((Link) link).getSourceTerm().addChild(link);
                        }
                }
            }

        } else {

            parent.addChild(child);

            if (child instanceof Term) {
                for (MapNode mapNode : deletedLinks) {
                    ((Link) mapNode).getSourceTerm().addChild(mapNode);
                }
            }
        }
    }

}
