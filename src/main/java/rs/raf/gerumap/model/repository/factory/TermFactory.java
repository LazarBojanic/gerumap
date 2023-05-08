package rs.raf.gerumap.model.repository.factory;

import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Term;

public class TermFactory extends MapNodeFactory{
    @Override
    public MapNode createMapNode(MapNodeComposite parent) {
        return new Term("Term", parent);
    }
}