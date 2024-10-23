package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class CancelMeetingService {

    private final AuthorizationService authz;
    private final MeetingRepository meetingRepository;

    public CancelMeetingService(final AuthorizationService authz, final MeetingRepository meetingRepository) {
        this.authz = authz;
        this.meetingRepository = meetingRepository;
    }

    public void cancelMeeting(MeetingTitle meetingTitle) {
        Meeting meeting = meetingRepository.findByMeetingById(meetingTitle);
        meeting.cancelMeeting();
        meetingRepository.save(meeting);
    }

    public Iterable<Meeting>   allMeetings() {
        return this.meetingRepository.findAll();
    }
}
