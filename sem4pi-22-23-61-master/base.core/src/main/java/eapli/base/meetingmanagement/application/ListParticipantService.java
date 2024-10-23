package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.InviteState;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ListParticipantService {
    private final InviteRepository inviteRepository;

    private final MeetingRepository meetingRepository;

    public ListParticipantService(MeetingRepository meetingRepository, InviteRepository inviteRepository){
        this.meetingRepository=meetingRepository;
        this.inviteRepository=inviteRepository;
    }

    public Meeting findByMeetingById(MeetingTitle meetingTitle){
        return meetingRepository.findByMeetingById(meetingTitle);
    }

    public Iterable<Invite> findInvitesByMeeting(Meeting meeting){
        return inviteRepository.findInvitesByMeeting(meeting);
    }
}
