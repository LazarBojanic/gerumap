package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class CannotChangeAuthor extends AbstractMessageEvent {

    public CannotChangeAuthor(Object source) {
        super(EventType.ERROR);
        this.setMessageDescription(MessageDescription.CANNOT_CHANGE_AUTHOR);
        this.setText("To change author please select a project or create a new one");
    }

}
