package eapli.base.Student_Teacher.Teacher.Repository;

import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends DomainRepository<Long, Teacher> {
    List<Teacher> findTeacherByAcronym(Acronym acronym);

    Optional<Teacher> ofIdentity(Username id);

    Teacher findTeacherBySystemUser(SystemUser systemUser);

    void deleteOfIdentity(Username entityId);
}

    /*
    public static Optional<Teacher> findTeacherByAcronym(Acronym acronym) {

        for (Teacher teacher : teachers) {
            if (teacher.getAcronym().equals(acronym)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }



    public default Optional<Teacher> findById(Long id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }

     */

