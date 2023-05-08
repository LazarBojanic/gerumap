package rs.raf.gerumap.centralizedProjectView.elementViewing;

import rs.raf.gerumap.observer.ISubscriber;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public interface IPainter extends ISubscriber {

    void paintElement(Graphics2D g);

    boolean isContained(Point p);

    boolean isContainedLasso(Rectangle2D rectangle2D);

    void setSelectionBorder(Graphics2D graphics2D);

    Shape createShape();
}
