package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class MeetingDate implements ValueObject {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date meetingDate;

    protected MeetingDate(){}

    public MeetingDate(Date meetingDate) {
        if (!isValidMeetingDate(meetingDate)) {
            throw new IllegalArgumentException("Invalid meeting date format");
        }
        this.meetingDate=meetingDate;
    }

    private static boolean isValidMeetingDate(Date meetingDate) {
        Date date = new Date(System.currentTimeMillis());
        return meetingDate.after(date);
    }

    @Override
    public String toString() {
        return meetingDate.toString();
    }

    public Date getMeetingDate(){return meetingDate;}
}
