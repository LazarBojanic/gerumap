package rs.raf.gerumap.commandManagement;

import rs.raf.gerumap.centralizedProjectView.TermPainter;
import rs.raf.gerumap.centralizedProjectView.elementViewing.ElementPainter;
import rs.raf.gerumap.commandManagement.abstraction.AbstractCommand;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.Term;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MoveCommand extends AbstractCommand {

    private Point oldPoint;
    private Point newPoint;
    private Term term;
    private LinkedList<ElementPainter> elementPainters;
    private List<MapNode> terms;

    public MoveCommand(Point oldPoint, Point newPoint, Term term) {
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
        this.term = term;
    }

    public MoveCommand(Point oldPoint, Point newPoint, LinkedList<ElementPainter> elementPainters) {
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
        this.elementPainters = elementPainters;
        terms = (elementPainters.stream().map(ElementPainter::getModel).collect(Collectors.toList()));
        System.out.println(terms);
        undoCommand();
    }

    @Override
    public void doCommand() {

        if (elementPainters == null) {
            term.setLocation(new Point(
                    newPoint.x - term.getEllipseDimension().width/2,
                    newPoint.y - term.getEllipseDimension().height/2
            ));
        } else {
//            for(ElementPainter elementPainter : elementPainters){
//                if(elementPainter instanceof TermPainter){
//                    Term term = (Term)(elementPainter.getModel());
//                    term.setLocation(new Point(term.getLocation().x + newPoint.x - oldPoint.x, term.getLocation().y + newPoint.y - oldPoint.y));
//                }
//            }
            for(MapNode mapNode : terms){
                if(mapNode instanceof Term){
                    Term term = ((Term) mapNode);
                    term.setLocation(new Point(term.getLocation().x + newPoint.x - oldPoint.x, term.getLocation().y + newPoint.y - oldPoint.y));
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getCurrentProjectView());
    }

    @Override
    public void undoCommand() {

        if (elementPainters == null) {
            term.setLocation(new Point(
                    oldPoint.x - term.getEllipseDimension().width/2,
                    oldPoint.y - term.getEllipseDimension().height/2
            ));
        } else {
//            for(ElementPainter elementPainter : elementPainters){
//                if(elementPainter instanceof TermPainter){
//                    Term term = (Term)(elementPainter.getModel());
//                    term.setLocation(new Point(term.getLocation().x - newPoint.x + oldPoint.x, term.getLocation().y - newPoint.y + oldPoint.y));
//                }
//            }
            for(MapNode mapNode : terms){
                if(mapNode instanceof Term){
                    Term term = ((Term) mapNode);
                    term.setLocation(new Point(term.getLocation().x - newPoint.x + oldPoint.x, term.getLocation().y - newPoint.y + oldPoint.y));
                }
            }
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getCurrentProjectView());
    }
}
