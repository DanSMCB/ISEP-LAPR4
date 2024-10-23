package eapli.base.persistence.impl.inmemory;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryInviteRepository extends InMemoryDomainRepository<Invite, Long>
        implements InviteRepository {

    static {
        InMemoryInitializer.init();
    }
    @Override
    public Iterable<Invite> findInvitesByReceiverUsername(Username username) {
        return match(e -> e.getReceiver().username().equals(username));
    }

    @Override
    public Iterable<Invite> findInvitesByMeeting(Meeting meeting) {
        return match(e -> e.getMeeting().getMeetingTitle().getTitle().equals(meeting.getMeetingTitle().getTitle()));
    }
}
