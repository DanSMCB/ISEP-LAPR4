package eapli.base.app.backoffice.console.presentation.meeting;
import eapli.framework.actions.Action;


public class CancelMeetingAction implements Action {
    @Override
    public boolean execute() {
        return new CancelMeetingUI().show();
    }
}
