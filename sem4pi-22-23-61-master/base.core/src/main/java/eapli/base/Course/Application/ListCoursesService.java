package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;

public class ListCoursesService {

    private final CourseRepository courseRepository;

    public ListCoursesService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Iterable<Course> courses() {
        return courseRepository.findAll();
    }
}
