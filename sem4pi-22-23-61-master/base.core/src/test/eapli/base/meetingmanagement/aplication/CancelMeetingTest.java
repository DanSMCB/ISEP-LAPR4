package eapli.base.meetingmanagement.aplication;

import eapli.base.meetingmanagement.domain.*;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelMeetingTest {

    @Test
    void cancelMeeting_MeetingStatusChangedToCanceled() {
        System.out.println("cancelMeeting - Meeting status changed to CANCELED");

        MeetingTitle meetingTitle = new MeetingTitle("Meeting 1");
        SystemUser systemUser = new SystemUser();
        MeetingDuration meetingDuration = new MeetingDuration(60);
        MeetingDate meetingDate = new MeetingDate("2023-06-18");
        MeetingTime meetingTime = new MeetingTime("14:30");
        Meeting meeting = new Meeting(meetingTitle, systemUser, meetingDuration, meetingDate, meetingTime);


        meeting.cancelMeeting();


        assertEquals(MeetingStatus.CANCELED, meeting.getStatus());
    }




















    private void assertTrue(boolean contains) {
    }
}
