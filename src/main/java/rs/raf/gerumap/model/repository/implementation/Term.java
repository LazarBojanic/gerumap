package rs.raf.gerumap.model.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.observer.NotificationType;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class Term extends MapNodeComposite {

    //private long hashValue;

    private Point location;
    private Dimension dimension;
    private Dimension ellipseDimension;

    private int fontSize;

    private int thickness;

    private int borderColor;
    private int backgroundColor;
    private int textColor;

    public Term(String name, MapNode parent) {
        super(name, parent);

        //hashValue = System.nanoTime();

        location = randomPoint();   //default values
        fontSize = 20;
        dimension = new Dimension((int) (fontSize*getName().length()*0.5), (int) (fontSize *1.8));

        ellipseDimension = new Dimension((int) (dimension.width*1.33), (int) (dimension.height*1.33));

        backgroundColor = Color.LIGHT_GRAY.getRGB();
        textColor = Color.BLACK.getRGB();
        thickness = 3;
        borderColor = Color.BLACK.getRGB();

    }
    public Term() {
        setName("");
        dimension = new Dimension((int) (fontSize*getName().length()*0.5), (int) (fontSize *1.8));
        ellipseDimension = new Dimension((int) (dimension.width*1.33), (int) (dimension.height*1.33));

        thickness = 3;
    }

    public void centralize(int fontSize) {
        this.fontSize = fontSize;
        setThickness(15);
        dimension = new Dimension((int) (fontSize*getName().length()*0.5), (int) (fontSize *1.8));
        ellipseDimension = new Dimension((int) (dimension.width*1.33), (int) (dimension.height*1.33));
    }

    public void decentralize(){
        this.fontSize = 20;
        setThickness(3);
        dimension = new Dimension((int) (fontSize*getName().length()*0.5), (int) (fontSize *1.8));
        ellipseDimension = new Dimension((int) (dimension.width*1.33), (int) (dimension.height*1.33));
    }

    private Point randomPoint(){

        int x = new Random().nextInt(MainFrame.getInstance().getWorkspacePanel().getComponent(0).getWidth());
        int y = new Random().nextInt(MainFrame.getInstance().getWorkspacePanel().getComponent(0).getHeight());

        return new Point(x, y);
    }
    @Override
    public void addChild(MapNode child) {
        if (child instanceof Link) {

            getChildren().add(child);

            ((Link) child).getDestinationTerm().addToThisAsDestinationTerm((Link) child);
            notifySubscribers(child, NotificationType.ADD);
        }
    }

    public void addToThisAsDestinationTerm(Link link){
        getChildren().add(link);
        notifySubscribers(link, NotificationType.ADD);
    }

    @Override
    public void deleteChild(MapNode child) {
        if (child instanceof Link) {

            Link link = ((Link) child);

            link.getSourceTerm().getChildren().remove(link);
            link.getSourceTerm().notifySubscribers(link, NotificationType.DELETE);

            link.getDestinationTerm().getChildren().remove(link);
            link.getDestinationTerm().notifySubscribers(link, NotificationType.DELETE);

        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void setLocation(Point location){

        if (location.getLocation().x < 2){
            location.setLocation(2, location.getLocation().y);
        }
        if (location.getLocation().x > 3000 - getEllipseDimension().width){
            location.setLocation(3000 - getEllipseDimension().width, location.getLocation().y);
        }
        if (location.getLocation().y < 2){
            location.setLocation(location.getLocation().x, 2);
        }
        if (location.getLocation().y > 2000 - getEllipseDimension().height){
            location.setLocation( location.getLocation().x, 2000 - getEllipseDimension().height);
        }

        this.location = location;
    }

    @Override
    public void setChildrenParents(){
        for (MapNode mapNode : getChildren()){

            Link link = ((Link) mapNode);

            if (this.getGuid().equals(link.getSourceTermGuid())){

                link.setParent(this);
                link.setSourceTerm(this);

                Term destinationTerm = findDestinationTerm(link.getDestinationTermGuid());
                link.setDestinationTerm(destinationTerm);
                destinationTerm.addToThisAsDestinationTerm(link);

            }
        }

        int i = 0;

        while (i < getChildren().size()){

            if (((Link) getChildren().get(i)).getSourceTerm() == null){
                getChildren().remove(getChildren().get(i));
            } else i++;
        }
    }

    /*private Term findDestinationTerm(long hashValue){
        MindMap mindMap = (MindMap) this.getParent();

        for (MapNode mapNode : mindMap.getChildren()){
            if(((Term) mapNode).getGuid() == hashValue){
                return (Term) mapNode;
            }
        }

        return null;
    }*/
    private Term findDestinationTerm(UUID guid){
        MindMap mindMap = (MindMap) this.getParent();

        for (MapNode mapNode : mindMap.getChildren()){
            if(mapNode.getGuid().equals(guid)){
                return (Term) mapNode;
            }
        }

        return null;
    }
}
