package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Domain.Course_ID;
import eapli.base.Course.Domain.Course_Name;
import eapli.base.Course.Repository.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class OpenCloseEnrollementsController {

    private final CourseService cs;

    public OpenCloseEnrollementsController(final AuthorizationService authz, final CourseRepository courseRepository) {
       cs = new CourseService(authz,courseRepository);
    }

    public Course openEnrollments(Course_Name courseName) {
        return cs.openEnrollments(courseName);
    }

    public Course closeEnrollments(Course_Name courseName) {
        return cs.closeEnrollments(courseName);
    }

}
