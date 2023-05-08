package rs.raf.gerumap.model.repository.composite;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import rs.raf.gerumap.model.repository.implementation.*;
import rs.raf.gerumap.observer.IPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public abstract class MapNodeComposite extends MapNode {
    private List<MapNode> children;

    public MapNodeComposite(final String name, final MapNode parent, final ArrayList<MapNode> children) {
        super(name, parent);
        this.children = children;
    }
    public MapNodeComposite(final String name, final MapNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }
    public MapNodeComposite() {
        this.children = new ArrayList<>();
    }
    public void setChildrenParents(){

        for(int i = 0; i < this.children.size(); i++){

            this.children.get(i).setParent(this);

            if(this.children.get(i) instanceof MapNodeComposite) {

                ((MapNodeComposite) this.children.get(i)).setChildrenParents();

            }
        }
    }
    public void printAllChildren(){
        for(int i = 0; i < this.children.size(); i++){
            System.out.println(this.children.get(i).getName());
            if(this.children.get(i) instanceof MapNodeComposite) {
                ((MapNodeComposite) this.children.get(i)).printAllChildren();
            }
        }
    }
    public abstract void addChild(final MapNode child);
    public abstract void deleteChild(final MapNode child);

    public MapNode getChildByName(final String name) {
        for (MapNode child: this.getChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }
    public MapNode getChildByGUID(final UUID guid) {
        for (MapNode child: this.getChildren()) {
            if (child.getGuid().equals(guid)) {
                return child;
            }
        }
        return null;
    }

    public boolean containsName(String name, MapNode node){
        for (MapNode mapNode : getChildren()){
            if (mapNode.getName().equals(name) && mapNode != node)
                return true;
        }
        return false;
    }

}