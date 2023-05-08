package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class CannotRemoveNodeMessage extends AbstractMessageEvent {
    public CannotRemoveNodeMessage(Object source){
        super(EventType.ERROR);
        this.setMessageDescription(MessageDescription.CANNOT_REMOVE_NODE);
        this.setText("Cannot remove: " + source.toString());
    }
}