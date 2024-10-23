package eapli.base.exammanagement.application;

import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public class ListFutureExamsService {

    private final AuthorizationService authz;
    private final ExamRepository examRepository;

    private final StudentRepository studentRepository;

    public ListFutureExamsService(final AuthorizationService authz, final ExamRepository examRepository, final StudentRepository studentRepository) {
        this.authz = authz;
        this.examRepository = examRepository;
        this.studentRepository=studentRepository;
    }

    /**
     * @return
     */
    public Iterable<Exam> futureExams(Student student) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT, BaseRoles.ADMIN);

        return examRepository.findStudentFutureExams(student);
    }

    public Student findStudentBySystemUser(SystemUser systemUser){
        return studentRepository.findStudentBySystemUser(systemUser);
    }
}
