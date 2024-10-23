package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class MeetingDuration implements ValueObject {

    private Integer value;

    protected MeetingDuration(){}

    public MeetingDuration(int value) {
        if (!isValidMeetingDuration(value)) {
            throw new IllegalArgumentException("Invalid meeting duration format");
        }
        this.value=value;
    }

    private static boolean isValidMeetingDuration(int value) {
        return value>0;
    }

    public Integer getValue(){return value;}

    @Override
    public String toString() {
        return value.toString();
    }


}
