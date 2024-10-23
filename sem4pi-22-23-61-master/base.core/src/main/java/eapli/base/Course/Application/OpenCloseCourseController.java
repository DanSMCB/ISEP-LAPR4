package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Domain.Course_ID;
import eapli.base.Course.Domain.Course_Name;
import eapli.base.Course.Repository.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class OpenCloseCourseController {

    private final CourseService cs;

    public OpenCloseCourseController(final AuthorizationService authz, final CourseRepository courseRepository) {
        cs = new CourseService(authz,courseRepository);
    }

    public Course openCourse(Course_Name courseName) {
        return cs.openCourse(courseName);
    }

    public Course closeCourse(Course_Name courseName) {
        return cs.closeCourse(courseName);
    }

}
