package rs.raf.gerumap.model.repository.composite;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import rs.raf.gerumap.model.repository.implementation.*;
import rs.raf.gerumap.observer.IPublisher;
import rs.raf.gerumap.observer.ISubscriber;
import rs.raf.gerumap.observer.NotificationType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Link.class, name = "link"),
        @JsonSubTypes.Type(value = MindMap.class, name = "mindMap"),
        @JsonSubTypes.Type(value = Project.class, name = "project"),
        @JsonSubTypes.Type(value = ProjectExplorer.class, name = "projectExplorer"),
        @JsonSubTypes.Type(value = Term.class, name = "term")
})
public abstract class MapNode implements IPublisher {
    private UUID guid;
    private String name;
    private transient MapNode parent;
    private transient List<ISubscriber> subscribers;

    public MapNode(String name, MapNode parent) {
        this.guid = UUID.randomUUID();
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>(7);
    }
    public MapNode() {
        subscribers = new ArrayList<>(7);
    }

//    @Override
//    public boolean equals(final Object obj) {
//
//        if (obj != null && obj instanceof MapNode) {
//            MapNode otherObj = (MapNode) obj;
//            return this.getName().equals(otherObj.getName());
//        }
//        return false;
//    }
    @Override
    public String toString() {
        return name;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        this.getSubscribers().add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.getSubscribers().remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification, NotificationType notificationType) {
        for (ISubscriber subscriber : subscribers)
            subscriber.update(notification, notificationType);
    }
    public void addMapNodeToTree(){

    }
    public void setName(String name) {
        this.name = name;
        this.notifySubscribers(this.getName(), NotificationType.NAME_CHANGE);
    }
}