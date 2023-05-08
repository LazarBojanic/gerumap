package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class CannotZoomAnymoreMessage extends AbstractMessageEvent {

    public CannotZoomAnymoreMessage(Object source) {
        super(EventType.NOTIFICATION);
        this.setMessageDescription(MessageDescription.CANNOT_ZOOM_ANYMORE);
        this.setText("Maximum zoom in count is 7");
    }

}
