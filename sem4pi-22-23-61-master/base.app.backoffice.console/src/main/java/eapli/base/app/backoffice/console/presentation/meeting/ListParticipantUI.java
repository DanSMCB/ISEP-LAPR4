package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meetingmanagement.application.ListParticipantController;
import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.InviteState;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.AbstractMap;
import java.util.List;

public class ListParticipantUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();
    MeetingRepository meetingRepository = PersistenceContext.repositories().meetingRepository();
    InviteRepository inviteRepository = PersistenceContext.repositories().inviteRepository();


    private final ListParticipantController listParticipantController = new ListParticipantController(meetingRepository, inviteRepository);

    @Override
    public String headline() {
        return "View participants of a meeting";
    }

    @Override
    protected boolean doShow() {

        final String meetingTitle = Console.readLine("Please enter the meeting title:");
        Meeting meeting = listParticipantController.getMeetingById(new MeetingTitle(meetingTitle));

        if (meeting == null) {
            System.out.println("No meeting found with the given ID.");
            return false;
        }

        Iterable<Invite> participantsStatus = listParticipantController.findInvitesByMeeting(meeting);

        System.out.println("Participants and their status:");

        while(participantsStatus.iterator().hasNext()){
            Invite invite = participantsStatus.iterator().next();
            System.out.println("User: " + invite.getReceiver().username().toString() + ", Status: " + invite.getState());
        }

        return false;
    }
}
