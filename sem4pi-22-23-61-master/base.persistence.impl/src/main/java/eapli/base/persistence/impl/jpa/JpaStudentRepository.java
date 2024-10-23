package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaBaseRepository;
import org.hibernate.jpa.internal.util.PersistenceUtilHelper;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaStudentRepository extends JpaAutoTxRepository<Student,MechanographicNumber, MechanographicNumber>
            implements StudentRepository {

    public JpaStudentRepository(final TransactionalContext autoTx) {
        super(autoTx, "mechanographic number");
    }

    public JpaStudentRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "mechanographic number");
    }

    @Override
    public Iterable<Student> findStudentByMechanographicNumberReturnList(String mechanographicNumber) {
        return null;
    }

    @Override
    public Student findStudentByMechanographicNumber(String mechanographicNumber) {
        final TypedQuery<Student> q = createQuery(
                "SELECT e FROM Student e WHERE e.mechanographicNumber = :mechanographicNumber", Student.class);
        q.setParameter("mechanographicNumber", mechanographicNumber);
        return q.getSingleResult();
    }

    @Override
    public Student findStudentBySystemUser(SystemUser systemUser) {
        final TypedQuery<Student> q = createQuery(
                "SELECT e FROM Student e WHERE e.systemUser.username.value = :systemUser", Student.class);
        q.setParameter("systemUser", systemUser.username().toString());
        return q.getSingleResult();
    }

    @Override
        public Optional<Student> findByUsername(final Username name) {
            final Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            return matchOne("e.systemUser.username=:name", params);
        }

        @Override
        public Optional<Student> findByMechanographicNumber(final MechanographicNumber number) {
            final Map<String, Object> params = new HashMap<>();
            params.put("number", number);
            return matchOne("e.mechanographicNumber=:number", params);
        }
    }
