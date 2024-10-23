package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.*;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ScheduleMeetingController {

    private final AuthorizationService authz;
    private final MeetingRepository meetingRepository;
    private final InviteRepository inviteRepository;

    public ScheduleMeetingController(final AuthorizationService authz, final MeetingRepository meetingRepository, InviteRepository inviteRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        this.authz= authz;
        this.meetingRepository=meetingRepository;
        this.inviteRepository=inviteRepository;
    }

    public Meeting saveMeeting(final MeetingTitle meetingTitle, final SystemUser systemUser, final MeetingDuration meetingDuration, final MeetingDate meetingDate, final MeetingTime meetingTime){
        return meetingRepository.save(new Meeting(meetingTitle, systemUser, meetingDuration, meetingDate, meetingTime));
    }

    public Invite saveInvite(final SystemUser sender, final SystemUser receiver, final Meeting meeting){
        return inviteRepository.save(new Invite(sender, receiver, meeting));
    }
}
