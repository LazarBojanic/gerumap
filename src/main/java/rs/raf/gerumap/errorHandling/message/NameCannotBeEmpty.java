package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class NameCannotBeEmpty extends AbstractMessageEvent {

    public NameCannotBeEmpty(Object source) {
        super(EventType.ERROR);
        this.setMessageDescription(MessageDescription.CANNOT_CHANGE_AUTHOR);
        this.setText("Name cannot be empty, please type at least one character");
    }

}
