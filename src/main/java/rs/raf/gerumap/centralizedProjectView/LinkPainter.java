package rs.raf.gerumap.centralizedProjectView;

import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.gui.SwingGui;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.Term;
import rs.raf.gerumap.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class LinkPainter extends ElementPainter {

    public LinkPainter(MapNode model) {
        super(model);
        model.addSubscriber(this);
    }

    @Override
    public void paintElement(Graphics2D g) {
        Link link = ((Link) getModel());

        g.setColor(new Color(link.getBorderColor()));
        g.setStroke(new BasicStroke(link.getThickness()));

        g.drawLine(
                link.getSourceTerm().getLocation().x + link.getSourceTerm().getEllipseDimension().width/2,
                 link.getSourceTerm().getLocation().y + link.getSourceTerm().getEllipseDimension().height/2,
                 link.getDestinationTerm().getLocation().x + link.getDestinationTerm().getEllipseDimension().width/2,
                 link.getDestinationTerm().getLocation().y + link.getDestinationTerm().getEllipseDimension().height/2
        );

        if (isSelected())
            setSelectionBorder(g);
    }

    @Override
    public boolean isContained(Point p) {
        if (createShape().contains(p))
            return true;
        return false;
    }

    @Override
    public boolean isContainedLasso(Rectangle2D rectangle2D) {
        if (createShape().intersects(rectangle2D))
            return true;
        return false;
    }

    @Override
    public void setSelectionBorder(Graphics2D graphics2D) {
        BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{5,5}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(createShape());
    }

    @Override
    public Shape createShape() {
        Link link = ((Link) getModel());

        GeneralPath generalPath = new GeneralPath();

        generalPath.moveTo(link.getSourceTerm().getLocation().x + link.getSourceTerm().getEllipseDimension().width/2
                        , link.getSourceTerm().getLocation().y + link.getSourceTerm().getEllipseDimension().height/2 + 10);
        generalPath.lineTo(link.getDestinationTerm().getLocation().x + link.getDestinationTerm().getEllipseDimension().width/2,
                        link.getDestinationTerm().getLocation().y + link.getDestinationTerm().getEllipseDimension().height/2 + 10);
        generalPath.lineTo(link.getDestinationTerm().getLocation().x + link.getDestinationTerm().getEllipseDimension().width/2
                        , link.getDestinationTerm().getLocation().y + link.getDestinationTerm().getEllipseDimension().height/2 - 10);
        generalPath.lineTo(link.getSourceTerm().getLocation().x + link.getSourceTerm().getEllipseDimension().width/2
                        , link.getSourceTerm().getLocation().y + link.getSourceTerm().getEllipseDimension().height/2 - 10);
        generalPath.closePath();

        setShape(generalPath);
        return getShape();
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {
        switch (notificationType){
            case REPAINT -> SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getCurrentProjectView());
        }
    }
}
