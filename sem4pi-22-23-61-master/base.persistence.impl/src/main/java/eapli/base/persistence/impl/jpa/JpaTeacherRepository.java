package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaTeacherRepository
        extends JpaAutoTxRepository<Teacher, Long, Long>
        implements TeacherRepository {

    public JpaTeacherRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaTeacherRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public List<Teacher> findTeacherByAcronym(Acronym acronym) {
        final TypedQuery<Teacher> query = entityManager().createQuery(
                "SELECT c FROM Teacher c WHERE c.acronym = :acronym",
                Teacher.class);
        query.setParameter("acronym", acronym);
        return query.getResultList();
    }

    @Override
    public Optional<Teacher> ofIdentity(Username id) {
        return Optional.empty();
    }

    @Override
    public Teacher findTeacherBySystemUser(SystemUser systemUser) {
        final TypedQuery<Teacher> q = entityManager().createQuery(
                "SELECT e FROM Teacher e WHERE e.systemUser.username.value = :systemUser",
                Teacher.class);
        q.setParameter("systemUser", systemUser.username().toString());
        return q.getSingleResult();
    }

    @Override
    public void deleteOfIdentity(Username entityId) {

    }
}
