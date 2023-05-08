package rs.raf.gerumap.errorHandling.logger;

import lombok.NoArgsConstructor;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.observer.NotificationType;
@NoArgsConstructor
public class ConsoleLogger implements ILogger {
    @Override
    public void log(final AbstractMessageEvent eventMessage) {
        System.out.println(eventMessage);
    }
    @Override
    public void update(final Object notification, NotificationType notificationType) {
        log((AbstractMessageEvent) notification);
    }
}