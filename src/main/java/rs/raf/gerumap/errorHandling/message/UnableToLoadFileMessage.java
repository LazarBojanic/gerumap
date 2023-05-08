package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class UnableToLoadFileMessage extends AbstractMessageEvent {

    public UnableToLoadFileMessage(Object source) {
        super(EventType.NOTIFICATION);
        this.setMessageDescription(MessageDescription.UNABLE_TO_LOAD_FILE);
        this.setText("Unable to load " + source + ",\nplease try with another file or check if file format is correct");
    }

}
