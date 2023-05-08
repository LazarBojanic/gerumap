package rs.raf.gerumap.centralizedProjectView.elementViewing;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.model.repository.composite.MapNode;

import java.awt.*;

@Getter
@Setter
public abstract class ElementPainter implements IPainter {

    private Shape shape;
    private MapNode model;
    private boolean isSelected;

    public ElementPainter(MapNode model) {
        this.model = model;
    }
}
