package rs.raf.gerumap.centralizedProjectView;

import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.Term;
import rs.raf.gerumap.observer.ISubscriber;
import rs.raf.gerumap.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class TermPainter extends ElementPainter implements ISubscriber {

    public TermPainter(MapNode model) {
        super(model);
        model.addSubscriber(this);
    }

    @Override
    public void paintElement(Graphics2D g) {

        Term term = ((Term) getModel());

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.setFont(new Font("Basic font", Font.PLAIN, term.getFontSize()));

        g.setColor(new Color(term.getBorderColor()));
        g.setStroke(new BasicStroke(term.getThickness()));
        getWidthFromText(g, term);
        g.drawOval(term.getLocation().x, term.getLocation().y, term.getEllipseDimension().width, term.getEllipseDimension().height);

        g.setColor(new Color(term.getBackgroundColor()));
        g.fillOval(term.getLocation().x, term.getLocation().y, term.getEllipseDimension().width, term.getEllipseDimension().height);

        g.setColor(new Color(term.getTextColor()));
        int offsetX = (term.getEllipseDimension().width - term.getDimension().width)/2;
        g.drawString(term.getName(), term.getLocation().x + offsetX, (int) (term.getLocation().y + term.getFontSize()*1.5));

        if (isSelected())
            setSelectionBorder(g);
    }

    @Override
    public boolean isContained(Point p) {

        Term term = ((Term) getModel());
        Ellipse2D ellipse2D = new Ellipse2D.Float(term.getLocation().x,term.getLocation().y,term.getEllipseDimension().width,term.getEllipseDimension().height);

        return ellipse2D.contains(p);
    }

    @Override
    public boolean isContainedLasso(Rectangle2D rectangle2D) {
        Term term = ((Term) getModel());
        Ellipse2D ellipse2D = new Ellipse2D.Float(term.getLocation().x,term.getLocation().y,term.getEllipseDimension().width,term.getEllipseDimension().height);

        return ellipse2D.intersects(rectangle2D);
    }

    @Override
    public void update(Object notification, NotificationType notificationType) {

        switch (notificationType){
            case NAME_CHANGE, REPAINT -> SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getCurrentProjectView().getSelectedComponent());

            case DELETE -> getModel().getParent().notifySubscribers(notification, NotificationType.DELETE);

            case ADD -> {
                if (this.getModel() == ((Link) notification).getSourceTerm()) {   //add this link to painter only if it has the same source term as this term painter
                    MindMapView mindMapView = ((MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView());
                    mindMapView.getElementPainters().add(0, new LinkPainter((MapNode) notification));
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getCurrentProjectView().getSelectedComponent());
    }

    public Shape createShape(){
        Term term = ((Term) getModel());

        GeneralPath generalPath = new GeneralPath();
        generalPath.moveTo(term.getLocation().x - term.getThickness()/2, term.getLocation().y - term.getThickness()/2);
        generalPath.lineTo(term.getLocation().x + term.getEllipseDimension().width + term.getThickness()/2, term.getLocation().y - term.getThickness()/2);
        generalPath.lineTo(term.getLocation().x + term.getEllipseDimension().width + term.getThickness()/2, term.getLocation().y + term.getEllipseDimension().height + term.getThickness()/2);
        generalPath.lineTo(term.getLocation().x - term.getThickness()/2, term.getLocation().y + term.getEllipseDimension().height + term.getThickness()/2);
        generalPath.closePath();

        setShape(generalPath);

        return getShape();
    }

    private void getWidthFromText(Graphics2D graphics2D, Term term){

        int width = graphics2D.getFontMetrics().stringWidth(term.getName());
        term.getDimension().width = Math.max(width, 10);
        term.getEllipseDimension().width = Math.max((int) (term.getDimension().width*1.33), 10);

    }

    public void setSelectionBorder(Graphics2D graphics2D){
        BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{5,5}, 0);
        graphics2D.setStroke(stroke);
        graphics2D.setColor(Color.BLACK);
        graphics2D.draw(createShape());
    }
}
