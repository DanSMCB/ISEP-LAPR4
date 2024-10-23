package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.meetingmanagement.domain.Meeting;
import eapli.framework.visitor.Visitor;

public class MeetingPrinter implements Visitor<Meeting> {

    @Override
    public void visit(Meeting visitee) {
        System.out.printf("Meeting ID: %s, Date: %s, Time: %s, Duration: %s minutes, Status: %s\n",
                visitee.identity(), visitee.getMeetingDate(), visitee.getMeetingTime(), visitee.getMeetingDuration(), visitee.getStatus());
    }
}
