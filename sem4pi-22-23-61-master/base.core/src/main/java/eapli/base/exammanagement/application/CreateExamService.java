package eapli.base.exammanagement.application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Adicionar Serviço de Exame
 * Created and Edited by João Cruz
 * Inspired and Based on Daniel Braga's and EAPLI's code
 */

@ApplicationService
public class CreateExamService {
    private final AuthorizationService authz;
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public CreateExamService(final AuthorizationService authz, final ExamRepository examRepository, final CourseRepository courseRepository) {
        this.authz = authz;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }

    public Iterable<Exam> courseExams(Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        return examRepository.findByCourse(course);
    }

    public Iterable<Course> courses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        return courseRepository.findAll();
    }
}
