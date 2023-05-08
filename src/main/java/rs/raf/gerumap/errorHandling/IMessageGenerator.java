package rs.raf.gerumap.errorHandling;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;

import java.io.IOException;

public interface IMessageGenerator {
    void generateMessage(MessageDescription messageDescription, Object source) throws IOException;
}