package rs.raf.gerumap.observer;

public interface ISubscriber {
    void update(Object notification, NotificationType notificationType);
}
