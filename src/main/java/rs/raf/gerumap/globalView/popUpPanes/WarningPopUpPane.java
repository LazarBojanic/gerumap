package rs.raf.gerumap.globalView.popUpPanes;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.popUpPanes.abstraction.PopUpPane;

import javax.swing.*;

public class WarningPopUpPane extends PopUpPane {

    public static int warningPaneState;
    @Override
    public Object showPopUp(AbstractMessageEvent eventMessage) {

        if(eventMessage.getEventType().equals(EventType.WARNING)){
            warningPaneState = JOptionPane.showConfirmDialog(MainFrame.getInstance(), eventMessage.getText(), eventMessage.getEventType().name(), JOptionPane.OK_CANCEL_OPTION);
        }

        return null;
    }
}
