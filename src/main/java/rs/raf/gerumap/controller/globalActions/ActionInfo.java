package rs.raf.gerumap.controller.globalActions;

import rs.raf.gerumap.controller.managementAndAbstraction.AbstractMapAction;
import rs.raf.gerumap.globalView.frame.BasicDialog;
import rs.raf.gerumap.globalView.frame.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ActionInfo extends AbstractMapAction {
    public ActionInfo() {
        super("infoIcon.png");
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Find info about developers");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BasicDialog jDialog = new BasicDialog(MainFrame.getInstance(), "Info", true, 375, 295);

        try {
            addDeveloperInfo(jDialog);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        jDialog.setVisible(true);
    }

    private void addDeveloperInfo(BasicDialog jDialog) throws IOException {

        JPanel infoPanel = new JPanel();
        //infoPanel.setLayout(new GridLayout(4, 2, 2, 1));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JPanel infoPanel2 = new JPanel();
        //infoPanel.setLayout(new GridLayout(4, 2, 2, 1));
        infoPanel2.setLayout(new BoxLayout(infoPanel2, BoxLayout.Y_AXIS));

        JLabel jLabelIme = new JLabel("Ime: Lazar");
        JLabel jLabelPrezime = new JLabel("Prezime: Bojanic");
        JLabel jLabelIndeks = new JLabel("Indeks: 116/21/RN");
        JLabel jLabelSlika = new JLabel("Slika:");

        JLabel jLabelIme2 = new JLabel("Ime: Mihailo");
        JLabel jLabelPrezime2 = new JLabel("Prezime: Djordjevic");
        JLabel jLabelIndeks2 = new JLabel("Indeks: 30/20/RN");
        JLabel jLabelSlika2 = new JLabel("Slika:");
        BufferedImage dev1Img = ImageIO.read(this.getClass().getResource("dev1.png"));
        BufferedImage dev2Img = ImageIO.read(this.getClass().getResource("dev2.png"));
        JLabel jLabelSlikaContent = new JLabel(new ImageIcon(new ImageIcon(dev1Img).getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
        jLabelSlikaContent.setBorder(new LineBorder(Color.BLACK, 1));
        JLabel jLabelSlikaContent2 = new JLabel(new ImageIcon(new ImageIcon(dev2Img).getImage().getScaledInstance(-1, 180, Image.SCALE_SMOOTH)));
        jLabelSlikaContent2.setBorder(new LineBorder(Color.BLACK, 1));

        infoPanel.add(jLabelIme); infoPanel2.add(jLabelIme2);
        infoPanel.add(jLabelPrezime); infoPanel2.add(jLabelPrezime2);
        infoPanel.add(jLabelIndeks); infoPanel2.add(jLabelIndeks2);
        infoPanel.add(jLabelSlika); infoPanel2.add(jLabelSlika2);

//        JPanel imagePanel = new JPanel();
//        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));

//        imagePanel.add(jLabelSlikaContent);
//        imagePanel.add(Box.createHorizontalStrut(30));
//        imagePanel.add(jLabelSlikaContent2);

        infoPanel.add(jLabelSlikaContent);
        infoPanel2.add(jLabelSlikaContent2);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        mainPanel.add(infoPanel);
        mainPanel.add(Box.createHorizontalStrut(12));
        mainPanel.add(infoPanel2);
        mainPanel.setBorder(new EmptyBorder(5,5,5,5));

        jDialog.add(mainPanel);
    }
}