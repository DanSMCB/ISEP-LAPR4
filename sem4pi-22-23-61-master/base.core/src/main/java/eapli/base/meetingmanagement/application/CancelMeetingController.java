package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class CancelMeetingController {
    private final CancelMeetingService cms;


    public CancelMeetingController(final AuthorizationService authz, final MeetingRepository meetingRepository) {
        cms = new CancelMeetingService(authz, meetingRepository);
    }
    public void cancelMeeting(MeetingTitle meetingTitle) {
        cms.cancelMeeting(meetingTitle);
    }

    public Iterable<Meeting> allMeetings() {
        return this.cms.allMeetings();
    }

}
