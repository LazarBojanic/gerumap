package rs.raf.gerumap.globalView.popUpPanes.editElementsPane;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.Link;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.model.repository.implementation.Term;
import rs.raf.gerumap.observer.NotificationType;
import rs.raf.gerumap.tree.model.abstraction.MapTreeItem;
;import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.LinkedList;

@Getter
@Setter
public class EditElementsPane extends JDialog {

    private JPanel mainPanel;

    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;

    private JPanel colorPanel;

    private JLabel borderColor;
    private JLabel backgroundColor;
    private JLabel textColor;

    private JButton borderButton;
    private JButton backgroundButton;
    private JButton textButton;

    private JLabel thicknessLabel;
    private JSlider thicknessSlider;

    private JColorChooser jColorChooserBackground;
    private JColorChooser jColorChooserBorder;
    private JColorChooser jColorChooserText;

    private JTextField renameField;

    private JDialog jDialog;

    private List<MapNode> model;

    /*public EditElementsPane(Frame parent, MapNode mapNode, String title, boolean modal, int sizeX, int sizeY) {

        this.model = mapNode;

        setSize(sizeX, sizeY);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - sizeX, Toolkit.getDefaultToolkit().getScreenSize().height/2 - sizeY);
        setTitle(title);

        initComponents();
        setComponentAttributes();

        this.add(mainPanel);
        setResizable(false);
        setVisible(true);
    }*/
    public EditElementsPane(Frame parent, List<MapNode> selectedElements, String title, boolean modal, int sizeX, int sizeY) {

        this.model = selectedElements;

        setSize(sizeX, sizeY);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - sizeX, Toolkit.getDefaultToolkit().getScreenSize().height/2 - sizeY);
        setTitle(title);

        initComponents();
        setComponentAttributes();

        this.add(mainPanel);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents(){

        mainPanel = new JPanel();

        colorPanel = new JPanel();

        buttonPanel = new JPanel();
        okButton = new JButton("Ok");
        cancelButton = new JButton("cancel");

        borderColor = new JLabel("borderColor");
        backgroundColor = new JLabel("backgroundColor");
        textColor = new JLabel("textColor");

        borderButton = new JButton();
        backgroundButton = new JButton();
        textButton = new JButton();

        jColorChooserBorder = new JColorChooser();
        jColorChooserText = new JColorChooser();
        jColorChooserBackground = new JColorChooser();

        thicknessLabel = new JLabel("border thickness");
        thicknessSlider = new JSlider(SwingConstants.HORIZONTAL,0, 20, 3);

        renameField = new JTextField();
        jDialog = new JDialog();

    }
    private void setComponentAttributes(){
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        colorPanel.setLayout(new FlowLayout());
        colorPanel.setPreferredSize(new Dimension(170, 139));

        borderColor.setPreferredSize(new Dimension(100, 40));
        backgroundColor.setPreferredSize(new Dimension(100, 40));
        textColor.setPreferredSize(new Dimension(100, 40));

        borderButton.setBackground(Color.BLACK);
        borderButton.setPreferredSize(new Dimension(40, 40));
        borderButton.addMouseListener(new ButtonMouseListener(this, 1));

        textButton.setBackground(Color.BLACK);
        textButton.setPreferredSize(new Dimension(40, 40));
        textButton.addMouseListener(new ButtonMouseListener(this, 3));

        backgroundButton.setBackground(Color.WHITE);
        backgroundButton.setPreferredSize(new Dimension(40, 40));
        backgroundButton.addMouseListener(new ButtonMouseListener(this, 2));

        setColorChooserUpdateLogic();

        colorPanel.add(borderColor); colorPanel.add(borderButton);
        colorPanel.add(backgroundColor); colorPanel.add(backgroundButton);
        colorPanel.add(textColor); colorPanel.add(textButton);

        thicknessLabel.setMaximumSize(new Dimension(150, 20));
        thicknessLabel.setHorizontalAlignment(JLabel.LEFT); thicknessLabel.setBackground(Color.red);

        thicknessSlider.setPreferredSize(new Dimension(150, 30));
        thicknessSlider.addChangeListener(e ->{

            for (MapNode element : model){
                if(element instanceof Term){
                    ((Term) element).setThickness(thicknessSlider.getValue());
                    element.notifySubscribers(null, NotificationType.REPAINT);
                } else if(element instanceof Link){
                    ((Link) element).setThickness(thicknessSlider.getValue());
                    element.notifySubscribers(null, NotificationType.REPAINT);
                }
            }
        });
        if(model.size() == 1){
            renameField.setText(model.get(0).toString());
        }
        else{
            renameField.setText("");
            renameField.setEditable(false);
        }
        renameField.setMaximumSize(new Dimension(150, 15));
        renameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                updateNodeName(renameField.getText());
            }
        });

        mainPanel.add(colorPanel);
        //mainPanel.add(thicknessLabel);
        mainPanel.add(thicknessSlider);
        mainPanel.add(renameField);
    }

    private void setColorChooserUpdateLogic(){

        jColorChooserBorder.getSelectionModel().addChangeListener(e -> {
            borderButton.setBackground(jColorChooserBorder.getColor());
            for (MapNode element : model){
                if(element instanceof Term){
                    ((Term) element).setBorderColor(jColorChooserBorder.getColor().getRGB());
                    element.notifySubscribers(null, NotificationType.REPAINT);
                } else if (element instanceof Link){
                    ((Link) element).setBorderColor(jColorChooserBorder.getColor().getRGB());
                    element.notifySubscribers(null, NotificationType.REPAINT);
                }
            }
        });
        jColorChooserText.getSelectionModel().addChangeListener(e -> {
            textButton.setBackground(jColorChooserText.getColor());
            for (MapNode element : model){
                if(element instanceof Term){
                    ((Term) element).setTextColor(jColorChooserText.getColor().getRGB());
                    element.notifySubscribers(null, NotificationType.REPAINT);
                }
            }
        });

        jColorChooserBackground.getSelectionModel().addChangeListener(e -> {
            backgroundButton.setBackground(jColorChooserBackground.getColor());
            for (MapNode element : model){
                if(element instanceof Term){
                    ((Term) element).setBackgroundColor(jColorChooserBackground.getColor().getRGB());
                    element.notifySubscribers(null, NotificationType.REPAINT);
                }
            }
        });
    }

    public JDialog getColorChooser(int type){

        if (jDialog.isShowing()) return null;

        jDialog = new JDialog();
        jDialog.setPreferredSize(new Dimension(500, 500));
        jDialog.setSize(new Dimension(500, 500));
        jDialog.setLocation(200, 200);

        switch (type){
            case 1 -> jDialog.add(jColorChooserBorder);
            case 2 -> jDialog.add(jColorChooserBackground);
            case 3 -> jDialog.add(jColorChooserText);
        }

        jDialog.setVisible(true);
        jDialog.setAlwaysOnTop(true);

        return jDialog;
    }

    private void updateNodeName(String name){
        if(model.size() == 1){
            model.get(0).setName(name);
        }
    }
}

