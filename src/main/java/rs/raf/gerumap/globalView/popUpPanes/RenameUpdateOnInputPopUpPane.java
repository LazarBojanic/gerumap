package rs.raf.gerumap.globalView.popUpPanes;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.BasicDialog;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
@Setter
public class RenameUpdateOnInputPopUpPane extends BasicDialog {

    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JTextField textField;
    private JButton okButton;
    private JButton cancelButton;
    private String originalName;
    private NotificationType notificationType;

    public RenameUpdateOnInputPopUpPane(Frame parent, String title, boolean modal, int sizeX, int sizeY, NotificationType notificationType) {
        super(parent, title, modal, sizeX, sizeY);
        this.notificationType = notificationType;
        initDialog();
        initListeners(this);
    }

    private void initDialog(){

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));

        textField = new JTextField();
        textField.setMaximumSize(new Dimension(180, 32));

        okButton = new JButton("Ok");
        okButton.setMaximumSize(new Dimension(60, 32));
        cancelButton = new JButton("Cancel");
        cancelButton.setMaximumSize(new Dimension(60, 32));

        buttonPanel.add(okButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(cancelButton);

        mainPanel.add(textField);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);
    }

    private void initListeners(RenameUpdateOnInputPopUpPane renameUpdateOnInputPopUpPane) {
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                updateNodeName(textField.getText());
            }
        });

        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                MapNode mapNode = ((MapTreeItem) MainFrame.getInstance().getMapTreeView().getLastSelectedPathComponent()).getModel();

                switch (notificationType){

                    case AUTHOR_CHANGE -> {

                        ((Project) mapNode).setAuthor(originalName);
                        mapNode.notifySubscribers(originalName, NotificationType.AUTHOR_CHANGE);

                    }
                    case NAME_CHANGE -> {

                        mapNode.setName(originalName);

                    }
                }
                renameUpdateOnInputPopUpPane.dispose();
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
        });

        okButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                MapNode mapNode = ((MapTreeItem) MainFrame.getInstance().getMapTreeView().getLastSelectedPathComponent()).getModel();

                if (!(mapNode instanceof ProjectExplorer) && ((MapNodeComposite) mapNode.getParent()).containsName(textField.getText(), mapNode)){

                    String[] str = new String[]{mapNode.getParent().getName(), mapNode.getClass().getSimpleName(), textField.getText()};
                    ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.CONTAINING_SAME_NAME, str);

                    if (WarningPopUpPane.warningPaneState == 2){
                        return;
                    }

                }

                if(textField.getText().equals("")){

                    switch (notificationType){    //this is to reset author name to old name

                        case AUTHOR_CHANGE -> {

                            ((Project) mapNode).setAuthor(originalName);
                            mapNode.notifySubscribers(originalName, NotificationType.AUTHOR_CHANGE);

                        }
                        case NAME_CHANGE -> {

                            mapNode.setName(originalName);
                        }
                    }
                    ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NAME_CANNOT_BE_EMPTY, null);
                }
                else renameUpdateOnInputPopUpPane.dispose();
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
        });
    }

    private void updateNodeName(String name){

        MapNode mapNode = ((MapTreeItem) MainFrame.getInstance().getMapTreeView().getLastSelectedPathComponent()).getModel();

        switch (notificationType){

            case AUTHOR_CHANGE -> {

                ((Project) mapNode).setAuthor(name);
                mapNode.notifySubscribers(name, NotificationType.AUTHOR_CHANGE);

            }
            case NAME_CHANGE -> {

                mapNode.setName(name);

            }
        }

    }
}
