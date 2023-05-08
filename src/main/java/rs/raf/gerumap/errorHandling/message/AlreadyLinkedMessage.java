package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class AlreadyLinkedMessage extends AbstractMessageEvent {

    public AlreadyLinkedMessage(Object source) {
        super(EventType.ERROR);
        this.setMessageDescription(MessageDescription.ALREADY_LINKED);
        this.setText(((Object[]) source)[0] + " is already connected to " + ((Object[]) source)[1] + ".\nDelete the existing link if you want to have this link orientation.");
    }

}
