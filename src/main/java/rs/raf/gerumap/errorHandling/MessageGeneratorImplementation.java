package rs.raf.gerumap.errorHandling;

import rs.raf.gerumap.errorHandling.message.*;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.observer.IPublisher;
import rs.raf.gerumap.observer.ISubscriber;
import rs.raf.gerumap.observer.NotificationType;
import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements IMessageGenerator, IPublisher {
    private List<ISubscriber> subscribers;
    public MessageGeneratorImplementation(){
        subscribers = new ArrayList<>();
    }
    @Override
    public void addSubscriber(final ISubscriber sub) {
        subscribers.add(sub);
    }
    @Override
    public void removeSubscriber(final ISubscriber sub) {
        subscribers.remove(sub);
    }
    @Override
    public void notifySubscribers(final Object notification, final NotificationType notificationType) {
        for (ISubscriber subscriber : subscribers){
            subscriber.update(notification, notificationType);
        }
    }
    @Override
    public void generateMessage(MessageDescription messageDescription, Object source) {
        switch (messageDescription){
            case CANNOT_REMOVE_NODE -> notifySubscribers(new CannotRemoveNodeMessage(source), NotificationType.MESSAGE);
            case WRONG_NODE_SELECTED -> notifySubscribers(new WrongNodeSelectedMessage(source), NotificationType.MESSAGE);
            case NO_NODE_SELECTED -> notifySubscribers(new NoNodeSelectedMessage(source), NotificationType.MESSAGE);
            case CANNOT_CHANGE_AUTHOR -> notifySubscribers(new CannotChangeAuthor(source), NotificationType.MESSAGE);
            case NAME_CANNOT_BE_EMPTY -> notifySubscribers(new NameCannotBeEmpty(source), NotificationType.MESSAGE);
            case CONTAINING_SAME_NAME -> notifySubscribers(new ContainingSameNameMessage(source), NotificationType.MESSAGE);
            case CANNOT_ZOOM_ANYMORE -> notifySubscribers(new CannotZoomAnymoreMessage(source), NotificationType.MESSAGE);
            case SUCCESSFULLY_SAVED -> notifySubscribers(new SuccessfullySavedMessage(source), NotificationType.MESSAGE);
            case UNABLE_TO_LOAD_FILE -> notifySubscribers(new UnableToLoadFileMessage(source), NotificationType.MESSAGE);
            case ALREADY_LINKED -> notifySubscribers(new AlreadyLinkedMessage(source), NotificationType.MESSAGE);
        }
    }
}