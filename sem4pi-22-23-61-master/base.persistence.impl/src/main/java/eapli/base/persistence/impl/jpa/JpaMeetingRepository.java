package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Classe.domain.Classe;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.domain.MeetingTitle;
import eapli.base.meetingmanagement.repository.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, Long, Long> implements MeetingRepository {

    public JpaMeetingRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaMeetingRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Meeting findByMeetingById(MeetingTitle meetingTitle) {
        final TypedQuery<Meeting> query = entityManager().createQuery("SELECT c FROM Meeting c WHERE c.meetingTitle.title = :title", Meeting.class);
        query.setParameter("title", meetingTitle.getTitle());
        return query.getSingleResult();
    }

    @Override
    public Iterable<Meeting> findAll() {
        return this.createQuery("SELECT m FROM Meeting m", Meeting.class).getResultList();
    }



}
