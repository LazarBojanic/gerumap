package rs.raf.gerumap.globalView.gui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.AbstractMessageEvent;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.globalView.popUpPanes.ErrorPopUpPane;
import rs.raf.gerumap.globalView.popUpPanes.NotificationPopUpPane;
import rs.raf.gerumap.globalView.popUpPanes.WarningPopUpPane;
import rs.raf.gerumap.observer.NotificationType;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@Setter
@NoArgsConstructor
public class SwingGui implements IGui {
    private MainFrame mainFrame;
    @Override
    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
    @Override
    public void update(Object notification, NotificationType notificationType) {

        AbstractMessageEvent abstractMessageEvent = ((AbstractMessageEvent) notification);

        switch (abstractMessageEvent.getEventType()){
            case ERROR ->  new ErrorPopUpPane().showPopUp(abstractMessageEvent);
            case WARNING -> new WarningPopUpPane().showPopUp(abstractMessageEvent);
            case NOTIFICATION -> new NotificationPopUpPane().showPopUp(abstractMessageEvent);
        }

    }
}