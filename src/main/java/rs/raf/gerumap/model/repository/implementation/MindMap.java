package rs.raf.gerumap.model.repository.implementation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.gerumap.commandManagement.CommandManager;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.IPublisher;
import rs.raf.gerumap.observer.ISubscriber;
import rs.raf.gerumap.observer.NotificationType;

import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MindMap extends MapNodeComposite {

    private boolean isTemplate;
    private int backgroundColor;

    private double zoom;
    private double savedZoom;
    private int zoomInCount = 0;

    private transient CommandManager commandManager;

    public MindMap(String name, MapNode parent) {
        super(name, parent);
        backgroundColor = 0;
        zoom = 1;
        savedZoom = 1;
        commandManager = new CommandManager();
    }
    public MindMap(){
        commandManager = new CommandManager();
    }
    @Override
    public void addChild(final MapNode child) {
        if (child instanceof Term) {
            getChildren().add(child);
            notifySubscribers(child, NotificationType.ADD);
        }
    }

    public void mergeMindMaps(MindMap other){

       other.setChildrenParents();

       for (MapNode mapNode : other.getChildren()){
           addChild(mapNode);
           mapNode.setParent(this);
       }

    }

    @Override
    public void deleteChild(final MapNode child) {

        if (child instanceof Term) {
            getChildren().remove(child);

            Term term = ((Term) child);

            while (!term.getChildren().isEmpty()){    //to prevent concurrent modification no iteration through list is possible so this is a solution
                term.deleteChild(term.getChildren().get(0));
            }

            notifySubscribers(child, NotificationType.DELETE);   //remove elementPainter
        }
    }

    public int getBackgroundColor() {
        Random r = new Random();
        if (backgroundColor == 0)
            setBackgroundColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)).getRGB());
        return backgroundColor;
    }
}
