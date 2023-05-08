package rs.raf.gerumap.commandManagement;

import rs.raf.gerumap.commandManagement.abstraction.AbstractCommand;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;

public class AddElementCommand extends AbstractCommand {

    private MapNodeComposite parent;
    private MapNode child;

    public AddElementCommand(MapNodeComposite parent, MapNode child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        parent.addChild(child);
    }

    @Override
    public void undoCommand() {
        parent.deleteChild(child);
    }
}
