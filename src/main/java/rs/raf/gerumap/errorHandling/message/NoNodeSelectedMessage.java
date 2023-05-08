package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class NoNodeSelectedMessage extends AbstractMessageEvent {

    public NoNodeSelectedMessage(Object source) {
        super(EventType.ERROR);
        this.setMessageDescription(MessageDescription.NO_NODE_SELECTED);
        this.setText("No node is currently selected for a desired action, please select a node and try again");
    }
}
