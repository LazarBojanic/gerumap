package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class SuccessfullySavedMessage extends AbstractMessageEvent {

    public SuccessfullySavedMessage(Object source) {
        super(EventType.NOTIFICATION);
        this.setMessageDescription(MessageDescription.SUCCESSFULLY_SAVED);
        this.setText("Successfully saved " + ((Object[]) source)[0] + "\nto " + ((Object[]) source)[1]);
    }

}
