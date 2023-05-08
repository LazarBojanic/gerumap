package rs.raf.gerumap.model.repository.implementation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.observer.IPublisher;
import rs.raf.gerumap.observer.ISubscriber;
import rs.raf.gerumap.observer.NotificationType;

import java.nio.file.Path;
@Getter
@Setter
@AllArgsConstructor
public class Project extends MapNodeComposite {

    private String author = "Author not specified";
    private Path resourcesPath;
    private transient boolean hasBeenDisplayed = false;

    public Project(String name, MapNode parent, String author, Path resourcesPath) {
        super(name, parent);
        this.author = author;
        this.resourcesPath = resourcesPath;
    }
    public Project() {
        //setName("");
    }
    @Override
    public void addChild(final MapNode child) {
        if (child instanceof MindMap) {
            getChildren().add(child);
            notifySubscribers(child, NotificationType.ADD);
        }
    }

    @Override
    public void deleteChild(final MapNode child) {
        if (child instanceof MindMap) {
            getChildren().remove(child);
            notifySubscribers(child, NotificationType.DELETE);
        }
    }

    public Project(String name, MapNode parent){
        super(name, parent);
    }

}
