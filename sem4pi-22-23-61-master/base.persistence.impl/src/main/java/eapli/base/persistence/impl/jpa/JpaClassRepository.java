package eapli.base.persistence.impl.jpa;
import eapli.base.Application;
import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.domain.Classe_Title;
import eapli.base.Classe.repository.ClassRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class JpaClassRepository extends JpaAutoTxRepository<Classe, Classe_Title, Classe_Title> implements ClassRepository {

    public JpaClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaClassRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "title");
    }

    @Override
    public List<Classe> findClassesByTeacher(String teacherAcronym) {
        final TypedQuery<Classe> query = createQuery("SELECT c FROM Class c WHERE c.teacherAcronym = :acronym", Classe.class);
        query.setParameter("acronym", teacherAcronym);
        return query.getResultList();
    }
    @Override
    public Iterable<Classe> findAll() {
        return this.createQuery("SELECT c FROM Classe c", Classe.class).getResultList();
    }

}
