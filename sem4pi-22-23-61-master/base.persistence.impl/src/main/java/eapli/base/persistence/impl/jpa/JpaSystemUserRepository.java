package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.systemUserManagement.SystemUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.*;
import java.util.List;

public class JpaSystemUserRepository extends JpaAutoTxRepository<SystemUser, Username, Username> implements SystemUserRepository {

    public JpaSystemUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "username");
    }

    public JpaSystemUserRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "username");
    }

    @Override
    public Iterable<SystemUser> findByUsername(Username name) {
        final TypedQuery<SystemUser> q = entityManager().createQuery(
                "SELECT e FROM SystemUser e WHERE e.username.value = :username", SystemUser.class);
        q.setParameter("username", name.toString());
        return q.getResultList();
    }
}
