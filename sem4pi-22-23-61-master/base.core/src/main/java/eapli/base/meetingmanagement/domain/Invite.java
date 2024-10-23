package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
@Table(name="Invite")
public class Invite implements AggregateRoot<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IDINVITE")
    private Long id;

    private Meeting meeting;

    private SystemUser sender;

    private SystemUser receiver;

    private InviteState state;

    public SystemUser getSender() {
        return sender;
    }

    public SystemUser getReceiver() {
        return receiver;
    }

    public Meeting getMeeting(){return meeting;}

    protected Invite(){}

    public Invite (SystemUser sender, SystemUser receiver, Meeting meeting){
        Preconditions.noneNull(sender, receiver, meeting);

        this.sender=sender;
        this.receiver=receiver;
        this.meeting=meeting;
        this.state=InviteState.PENDING;
    }

    public void addMeetingParticipants(SystemUser systemUser){
        this.meeting.addParticipants(systemUser);
    }

    public void setState(InviteState inviteState){
        this.state=inviteState;
    }

    public InviteState getState() {
        return state;
    }

    @Override
    public boolean sameAs(Object other) {
        Invite invite = (Invite) other;
        return this.equals(invite);
    }

    @Override
    public Long identity() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public String toString() {
        return "You've been invited by " + sender.email().toString() + " to a meeting on the date " + meeting.getMeetingDate().getMeetingDate().toString() +
                " at " + meeting.getMeetingTime().getMeetingTime() + " with the dutarion of " + meeting.getMeetingDuration().toString();
    }


}
