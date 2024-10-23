package eapli.base.exammanagement.application;

import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

public class ListFutureExamsController {

    private final ListFutureExamsService svc;

    public ListFutureExamsController(final AuthorizationService authz, final ExamRepository examRepository, final StudentRepository studentRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        svc = new ListFutureExamsService(authz, examRepository, studentRepository);
    }

    /**
     * List all exams.
     *
     * @return
     */
        public Iterable<Exam> listFutureExams(Student student) {
        return svc.futureExams(student);
    }

    public Student findStudentBySystemUser(SystemUser systemUser){
            return svc.findStudentBySystemUser(systemUser);
    }

}

