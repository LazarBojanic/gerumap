package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class WrongNodeSelectedMessage extends AbstractMessageEvent {

    public WrongNodeSelectedMessage(Object source){
        super(EventType.ERROR);
        this.setMessageDescription(MessageDescription.WRONG_NODE_SELECTED);
        String[] nodes = (String[]) source;
        this.setText("Wrong node selected. Trying to add new " + nodes[0] + " to " + nodes[1] +
                        ".\nSelect or create a node that is of type " + nodes[2] + " and try again.");
    }

}
