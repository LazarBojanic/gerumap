package rs.raf.gerumap.model.repository.factory;

import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.model.repository.implementation.Project;

public class MindMapFactory extends MapNodeFactory{
    @Override
    public MapNode createMapNode(MapNodeComposite parent) {
        return new MindMap("Mind Map", parent);
    }
}
