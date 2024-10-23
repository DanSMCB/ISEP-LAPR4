package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meetingmanagement.application.CancelMeetingController;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CancelMeetingUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final CancelMeetingController cancelMeetingController = new CancelMeetingController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().meetingRepository());

    @Override
    public String headline() {
        return "Cancel a meeting";
    }

    @Override
    protected boolean doShow() {
        final Iterable<Meeting> meetings = cancelMeetingController.allMeetings();
        final SelectWidget<Meeting> selector = new SelectWidget<>("Meetings:", meetings, new MeetingPrinter());
        selector.show();
        final Meeting meeting = selector.selectedElement();
        if (meeting != null) {
            cancelMeetingController.cancelMeeting(meeting.getMeetingTitle());
            System.out.println("Meeting with ID " + meeting.identity() + " has been cancelled.");
        }
        return false;
    }
}
