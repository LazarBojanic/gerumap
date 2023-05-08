package rs.raf.gerumap.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.gerumap.commandManagement.CommandManager;
import rs.raf.gerumap.errorHandling.logger.ConsoleLogger;
import rs.raf.gerumap.errorHandling.logger.FileLogger;
import rs.raf.gerumap.errorHandling.MessageGeneratorImplementation;
import rs.raf.gerumap.globalView.gui.IGui;
import rs.raf.gerumap.editorMindMap.StateManager;
import rs.raf.gerumap.serializer.ISerializer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Setter
@Getter
@NoArgsConstructor
public class ApplicationFramework {
    public static final Path workingDirectory = Paths.get(System.getProperty("user.dir"));
    private StateManager stateManager;
    protected MessageGeneratorImplementation messageGeneratorImplementation;
    protected ConsoleLogger consoleLogger;
    protected FileLogger fileLogger;
    private static ApplicationFramework instance;
    protected IGui IGui;
    protected IMapRepository iMapRepository;
    private CommandManager commandManager;
    private ISerializer iSerializer;
    public void run(){
        this.IGui.start();
    }
    public void initialise(IGui IGui, IMapRepository mapRepository, CommandManager commandManager, ISerializer iSerializer) {
        this.commandManager = commandManager;
        this.IGui = IGui;
        this.iMapRepository = mapRepository;
        this.iSerializer = iSerializer;
        initialiseLogger();
        manageSubscriptions();
    }
    public void initialiseLogger(){
        messageGeneratorImplementation = new MessageGeneratorImplementation();
        consoleLogger = new ConsoleLogger();
        fileLogger = new FileLogger();
    }
    public void manageSubscriptions(){
        messageGeneratorImplementation.addSubscriber(IGui);
        messageGeneratorImplementation.addSubscriber(consoleLogger);
        messageGeneratorImplementation.addSubscriber(fileLogger);
    }
    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
