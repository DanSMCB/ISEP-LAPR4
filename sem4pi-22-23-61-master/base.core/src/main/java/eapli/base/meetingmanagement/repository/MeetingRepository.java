package eapli.base.meetingmanagement.repository;

import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.framework.domain.repositories.DomainRepository;

public interface MeetingRepository extends DomainRepository<Long, Meeting> {

    Meeting findByMeetingById(MeetingTitle meetingTitle);

    Iterable<Meeting> findAll();


}