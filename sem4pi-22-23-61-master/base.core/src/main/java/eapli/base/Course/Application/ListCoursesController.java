package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;

public class ListCoursesController {

    private final ListCoursesService svc;

    public ListCoursesController(final CourseRepository courseRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        svc = new ListCoursesService(courseRepository);
    }

    public Iterable<Course> listCourses() {
        return svc.courses();
    }
}
