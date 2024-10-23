package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.InviteState;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.AbstractMap;
import java.util.List;

public class ListParticipantController {

    private final ListParticipantService listParticipantService;

    public ListParticipantController(MeetingRepository meetingRepository, InviteRepository inviteRepository) {
        this.listParticipantService = new ListParticipantService(meetingRepository, inviteRepository);
    }
    public Meeting getMeetingById(MeetingTitle meetingTitle) {
        return listParticipantService.findByMeetingById(meetingTitle);
    }

    public Iterable<Invite> findInvitesByMeeting(Meeting meeting) {
        return listParticipantService.findInvitesByMeeting(meeting);
    }

}
