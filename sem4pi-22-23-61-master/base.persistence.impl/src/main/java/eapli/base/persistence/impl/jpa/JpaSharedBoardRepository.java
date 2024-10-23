package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.SharedBoard.aplication.SharedBoardArchiveController;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.SharedBoard.domain.Shared_Board_Title;
import eapli.base.SharedBoard.repository.SharedBoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.*;

class JpaSharedBoardRepository extends JpaAutoTxRepository<SharedBoard, Shared_Board_Title, Shared_Board_Title> implements SharedBoardRepository {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Application.settings().getPersistenceUnitName());
    EntityManager em = emf.createEntityManager();

    public JpaSharedBoardRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaSharedBoardRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public Iterable<SharedBoard> findSharedBoardsByOwner(SystemUser owner) {
        final TypedQuery<SharedBoard> q = entityManager().createQuery(
                "SELECT sb FROM SharedBoard sb WHERE sb.owner = :owner", SharedBoard.class);
        q.setParameter("owner", owner);
        return q.getResultList();
    }
    public Iterable<SharedBoard> findByBoardId(Shared_Board_Title title){
        final TypedQuery<SharedBoard> q = entityManager().createQuery(
                "SELECT sb FROM SharedBoard sb WHERE sb.title = :title", SharedBoard.class);
        q.setParameter("title", title);
        return q.getResultList();
    }

    public Iterable<SharedBoard> findBoardsBySystemUser(SystemUser systemUser){
        final TypedQuery<SharedBoard> q = entityManager().createQuery(
                "SELECT e FROM SharedBoard e WHERE KEY(e.sharedUsers) = :systemUser", SharedBoard.class);
        q.setParameter("systemUser", systemUser);
        return q.getResultList();
    }

    @Override
    public Iterable<SharedBoard> findAll() {
        return this.createQuery("SELECT s FROM SharedBoard s", SharedBoard.class).getResultList();
    }

}

