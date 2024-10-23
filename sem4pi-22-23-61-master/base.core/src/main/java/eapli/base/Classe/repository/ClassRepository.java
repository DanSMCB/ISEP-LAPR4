package eapli.base.Classe.repository;

import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.domain.Classe_Title;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ClassRepository extends DomainRepository<Classe_Title, Classe> {
    List<Classe> findClassesByTeacher(String teacherAcronym);

}
