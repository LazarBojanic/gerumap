package rs.raf.gerumap.globalView.popUpPanes.editElementsPane;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonMouseListener implements MouseListener {

    private EditElementsPane editElementsPane;
    private int type;

    public ButtonMouseListener(EditElementsPane editElementsPane, int type) {
        this.editElementsPane = editElementsPane;
        this.type = type;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        editElementsPane.getColorChooser(type);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
