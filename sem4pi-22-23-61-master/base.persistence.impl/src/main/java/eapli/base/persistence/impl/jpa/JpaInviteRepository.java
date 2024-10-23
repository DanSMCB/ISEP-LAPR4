package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.InviteState;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.*;
import java.util.Optional;

public class JpaInviteRepository extends JpaAutoTxRepository<Invite, Long, Long> implements InviteRepository {

    public JpaInviteRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaInviteRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Invite> findInvitesByReceiverUsername(Username username) {
        final TypedQuery<Invite> q = entityManager().createQuery(
                "SELECT e FROM Invite e WHERE e.receiver.username.value = :username AND e.state= :state", Invite.class);
        q.setParameter("username", username.toString());
        q.setParameter("state", InviteState.PENDING);
        return q.getResultList();
    }

    @Override
    public Iterable<Invite> findInvitesByMeeting(Meeting meeting) {
        final TypedQuery<Invite> q = entityManager().createQuery(
                "SELECT e FROM Invite e WHERE e.meeting.meetingTitle.title = :meeting", Invite.class);
        q.setParameter("meeting", meeting.getMeetingTitle().getTitle());
        return q.getResultList();
    }
}
