package rs.raf.gerumap.errorHandling.logger;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.observer.ISubscriber;

import java.io.IOException;

public interface ILogger extends ISubscriber {
     void log(final AbstractMessageEvent eventMessage) throws IOException;

}