package eapli.base.ExtraClasse.repository;

import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.domain.Classe_Title;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import eapli.base.ExtraClasse.domain.ExtraClasse_Title;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface ExtraClasseRepository extends DomainRepository<ExtraClasse_Title, ExtraClasse> {
    List<ExtraClasse> findExtraClassesByTeacher(String teacherAcronym);

}
