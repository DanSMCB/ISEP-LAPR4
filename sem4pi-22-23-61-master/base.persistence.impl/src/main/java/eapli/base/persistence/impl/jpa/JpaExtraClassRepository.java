package eapli.base.persistence.impl.jpa;
import eapli.base.Application;
import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.domain.Classe_Title;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import eapli.base.ExtraClasse.domain.ExtraClasse_Title;
import eapli.base.ExtraClasse.repository.ExtraClasseRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class JpaExtraClassRepository extends JpaAutoTxRepository<ExtraClasse, ExtraClasse_Title, ExtraClasse_Title> implements ExtraClasseRepository {

    public JpaExtraClassRepository(final TransactionalContext autoTx) {
        super(autoTx, "title");
    }

    public JpaExtraClassRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "title");
    }

    @Override
    public List<ExtraClasse> findExtraClassesByTeacher(String teacherAcronym) {
        final TypedQuery<ExtraClasse> query = createQuery("SELECT c FROM ExtraClass c WHERE c.teacherAcronym = :acronym", ExtraClasse.class);
        query.setParameter("acronym", teacherAcronym);
        return query.getResultList();
    }
    @Override
    public Iterable<ExtraClasse> findAll() {
        return this.createQuery("SELECT c FROM ExtraClasse c", ExtraClasse.class).getResultList();
    }


}