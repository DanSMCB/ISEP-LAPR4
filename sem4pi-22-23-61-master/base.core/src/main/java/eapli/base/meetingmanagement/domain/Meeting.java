package eapli.base.meetingmanagement.domain;

import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meeting implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDMEETING")
    private Long id;

    private MeetingTitle meetingTitle;

    private SystemUser systemUser;

    private MeetingDuration meetingDuration;

    private MeetingDate meetingDate;

    private MeetingTime meetingTime;
    @OneToMany
    private List<SystemUser> participants;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    private MeetingStatus status;

    protected Meeting() {
    }

    public Meeting (MeetingTitle meetingTitle, SystemUser systemUser, MeetingDuration meetingDuration, MeetingDate meetingDate, MeetingTime meetingTime){
        Preconditions.noneNull(systemUser, meetingDuration, meetingDate, meetingTime);

        this.meetingTitle=meetingTitle;
        this.systemUser=systemUser;
        this.meetingDuration=meetingDuration;
        this.meetingDate=meetingDate;
        this.meetingTime=meetingTime;
        this.participants=new ArrayList<>();
        this.status = MeetingStatus.SCHEDULED;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        Meeting meeting = (Meeting) other;
        return this.equals(meeting) && getMeetingDate().equals(meeting.getMeetingDate()) && getMeetingDuration().equals(meeting.getMeetingDuration()) && getMeetingTime().equals(meeting.getMeetingTime());
    }

    public void addParticipants(SystemUser systemUser){
        participants.add(systemUser);
    }

    @Override
    public Long identity() {
        return id;
    }

    public MeetingTitle getMeetingTitle() {
        return meetingTitle;
    }

    public MeetingDate getMeetingDate() {
        return meetingDate;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public MeetingDuration getMeetingDuration() {
        return meetingDuration;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public List<SystemUser> getParticipants() {
        return participants;
    }

    public MeetingStatus getStatus() {
        return status;
    }
    public void cancelMeeting() {
        this.status = MeetingStatus.CANCELED;
    }

}
