package rs.raf.gerumap.errorHandling.message.abstractionAndEnums;

import lombok.*;

import java.awt.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractMessageEvent {
    private String text;
    private EventType eventType;
    private Timestamp timestamp;
    private MessageDescription messageDescription;
    @Override
    public String toString() {
        return String.format("Event type: [%s]\nTimestamp: [%s]\nText: [%s]\n", this.eventType, this.timestamp, this.text);
    }
    public AbstractMessageEvent(final EventType eventType){
        this.eventType = eventType;
        timestamp = new Timestamp(System.currentTimeMillis());
        Toolkit.getDefaultToolkit().beep();
    }
}