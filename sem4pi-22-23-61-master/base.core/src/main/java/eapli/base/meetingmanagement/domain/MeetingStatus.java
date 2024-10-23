package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;


public enum MeetingStatus {
    SCHEDULED, COMPLETED, CANCELED;
}
