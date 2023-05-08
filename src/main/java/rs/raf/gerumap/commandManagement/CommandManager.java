package rs.raf.gerumap.commandManagement;

import rs.raf.gerumap.Main;
import rs.raf.gerumap.commandManagement.abstraction.AbstractCommand;
import rs.raf.gerumap.globalView.frame.MainFrame;

import java.util.ArrayList;

public class CommandManager {

    private int currentCommandIndex;
    private ArrayList<AbstractCommand> commands;

    public CommandManager() {
        commands = new ArrayList<>(10);
        currentCommandIndex = 0;
    }

    public void addCommand(AbstractCommand command){

        while (currentCommandIndex < commands.size()){
            commands.remove(currentCommandIndex);
        }

        commands.add(command);
        redoCommand();

    }

    public void redoCommand(){

        if (currentCommandIndex < commands.size())
            commands.get(currentCommandIndex++).doCommand();

        if (currentCommandIndex == commands.size()) {
            MainFrame.getInstance().getActionManager().getActionRedo().setEnabled(false);
        }

        MainFrame.getInstance().getActionManager().getActionUndo().setEnabled(true);

    }

    public void undoCommand(){

        if (currentCommandIndex > 0){
            commands.get(--currentCommandIndex).undoCommand();
        }

        if (currentCommandIndex == 0){
            MainFrame.getInstance().getActionManager().getActionUndo().setEnabled(false);
        }

        MainFrame.getInstance().getActionManager().getActionRedo().setEnabled(true);

    }
}
