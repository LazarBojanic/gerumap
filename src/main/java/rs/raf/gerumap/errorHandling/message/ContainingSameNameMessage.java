package rs.raf.gerumap.errorHandling.message;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

public class ContainingSameNameMessage extends AbstractMessageEvent {

    public ContainingSameNameMessage(Object source) {
        super(EventType.WARNING);
        String str[] = (String[]) source;
        this.setMessageDescription(MessageDescription.CONTAINING_SAME_NAME);
        this.setText(str[0] + " already contains " + str[1] + " named " + str[2] + ".\n Do you want to continue?");
    }

}
