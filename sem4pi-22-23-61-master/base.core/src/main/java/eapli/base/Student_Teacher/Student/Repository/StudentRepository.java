package eapli.base.Student_Teacher.Student.Repository;


import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public interface StudentRepository extends DomainRepository<MechanographicNumber, Student> {
    Map<MechanographicNumber, Student> students = new HashMap<>();

    Iterable <Student> findStudentByMechanographicNumberReturnList(String mechanographicNumber);
    Student findStudentByMechanographicNumber(String mechanographicNumber);

    Student findStudentBySystemUser(SystemUser systemUser);

    default Student findStudentByMechanographicNumber(MechanographicNumber mechanographicNumber) {
        return students.get(mechanographicNumber);
    }

     default void addStudent(Student student) {
        students.put(student.getMechanographicNumber(), student);
    }

    default void removeStudent(Student student){
        students.remove(student);
    }

    default void removeStudentByMechanographicNum(MechanographicNumber mechanographicNumber){
        students.remove(mechanographicNumber);
    }

    default Student getStudentByMechanographicNum(MechanographicNumber mechanographicNumber){
        return students.get(mechanographicNumber);
    }

    Optional<Student> findByUsername(Username name);

    Optional<Student> findByMechanographicNumber(MechanographicNumber number);
}