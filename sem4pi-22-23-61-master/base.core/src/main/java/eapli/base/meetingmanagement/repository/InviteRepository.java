package eapli.base.meetingmanagement.repository;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

public interface InviteRepository extends DomainRepository<Long, Invite> {

    Iterable<Invite> findInvitesByReceiverUsername(Username username);

    Iterable<Invite> findInvitesByMeeting(Meeting meeting);
}