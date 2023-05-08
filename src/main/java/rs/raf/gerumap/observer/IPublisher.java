package rs.raf.gerumap.observer;

import java.io.IOException;

public interface IPublisher {
    void addSubscriber(ISubscriber sub);
    void removeSubscriber(ISubscriber sub);
    void notifySubscribers(Object notification, NotificationType notificationType) throws IOException;
}