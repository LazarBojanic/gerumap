package rs.raf.gerumap.errorHandling.logger;

import org.apache.commons.io.FileUtils;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.observer.NotificationType;

import java.io.File;
import java.io.IOException;
import static rs.raf.gerumap.core.ApplicationFramework.workingDirectory;
public class FileLogger implements ILogger {
    private final File file;
    public FileLogger() {
        file = workingDirectory.resolve("src/main/resources/rs/raf/gerumap/errorHandling/logger/log.txt").toFile();
        if(file.exists()){
            try{
                FileUtils.delete(file);
            }
            catch(IOException e){
                System.err.println("File IO Error");
            }
        }
    }
    @Override
    public void log(final AbstractMessageEvent eventMessage) {
        try{
            FileUtils.writeStringToFile(file, eventMessage.toString() + "\n", "UTF-8", true);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void update(final Object notification, NotificationType notificationType) {
        log((AbstractMessageEvent) notification);
    }
}