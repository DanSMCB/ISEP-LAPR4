package eapli.base.exammanagement.application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.util.Iterator;

public class ListCourseExamsController {

    private final ListCourseExamsService svc;

    public ListCourseExamsController(final AuthorizationService authz, final ExamRepository examRepository, final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        svc = new ListCourseExamsService(authz, examRepository, courseRepository);
    }

    /**
     * List all exams.
     *
     * @return
     */
    public Iterable<Exam> listCourseExams(Course course) {
        return svc.courseExams(course);
    }

    public Iterable<Course> listCourses() {
        return svc.courses();
    }
}
