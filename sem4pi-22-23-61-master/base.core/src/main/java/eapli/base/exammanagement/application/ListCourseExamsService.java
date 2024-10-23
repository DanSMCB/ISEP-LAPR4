package eapli.base.exammanagement.application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Iterator;

@ApplicationService
class ListCourseExamsService {
    private final AuthorizationService authz;
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public ListCourseExamsService(final AuthorizationService authz, final ExamRepository examRepository, final CourseRepository courseRepository) {
        this.authz = authz;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * @return
     */
    public Iterable<Exam> courseExams(Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        return examRepository.findByCourse(course);
    }

    public Iterable<Course> courses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        return courseRepository.findAll();
    }
}