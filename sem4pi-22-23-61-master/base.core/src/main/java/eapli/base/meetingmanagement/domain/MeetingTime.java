package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class MeetingTime implements ValueObject {

    @DateTimeFormat(pattern = "hh:mm")
    private Date meetingTime;

    protected MeetingTime(){}

    public MeetingTime(Date meetingTime) {this.meetingTime=meetingTime;
    }

    public Date getMeetingTime(){return meetingTime;}

    @Override
    public String toString() {
        return meetingTime.toString();
    }
}
