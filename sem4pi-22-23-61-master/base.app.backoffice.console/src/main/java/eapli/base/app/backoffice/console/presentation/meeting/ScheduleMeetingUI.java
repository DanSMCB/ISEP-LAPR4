package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.app.backoffice.console.presentation.authz.SystemUserPrinter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meetingmanagement.application.ScheduleMeetingController;
import eapli.base.meetingmanagement.domain.*;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Date;

public class ScheduleMeetingUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final ListUsersController usersController = new ListUsersController();

    private final ScheduleMeetingController scheduleMeetingController = new ScheduleMeetingController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().meetingRepository(), PersistenceContext.repositories().inviteRepository());

    @Override
    public String headline() {
        return "Schedule a meeting";
    }

    @Override
    protected boolean doShow() {
        final String title = Console.readLine("Type the meeting title.");
        final Date date = Console.readDate("Type the meeting date (dd-MM-yyyy):", "dd-MM-yyyy");
        final Date time = Console.readDate("Type the meeting time (HH:mm):", "HH:mm");
        final int duration = Console.readInteger("Type the meeting duration in minutes:");
        Meeting meeting = scheduleMeetingController.saveMeeting(new MeetingTitle(title), myUser, new MeetingDuration(duration), new MeetingDate(date), new MeetingTime(time));

        final Iterable<SystemUser> systemUsers = this.usersController.allUsers();
        int option;
        do{
            final SelectWidget<SystemUser> selector = new SelectWidget<>("Users:", systemUsers, new SystemUserPrinter());
            selector.show();
            final SystemUser systemUser = selector.selectedElement();
            if (systemUser != null) {
                scheduleMeetingController.saveInvite(myUser, systemUser, meeting);
                System.out.println("Invite sent to " + systemUser.username());
            }
            option=Console.readInteger("Type \"0\" to exit or any other button to invite another user.");
        }while(option!=0);


        return false;
    }
}
