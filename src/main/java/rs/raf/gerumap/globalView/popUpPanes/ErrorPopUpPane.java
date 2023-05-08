package rs.raf.gerumap.globalView.popUpPanes;

import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.EventType;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.popUpPanes.abstraction.PopUpPane;

import javax.swing.*;

public class ErrorPopUpPane extends PopUpPane {
    @Override
    public Object showPopUp(AbstractMessageEvent eventMessage) {
        if(eventMessage.getEventType().equals(EventType.ERROR)){
            JOptionPane.showMessageDialog(MainFrame.getInstance(), eventMessage.getText(), eventMessage.getEventType().name(), JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
}