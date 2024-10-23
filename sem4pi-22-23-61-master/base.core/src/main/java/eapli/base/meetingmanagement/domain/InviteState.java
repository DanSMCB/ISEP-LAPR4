package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;

public enum InviteState implements ValueObject {
    PENDING, ACCEPTED, REJECTED;
}
