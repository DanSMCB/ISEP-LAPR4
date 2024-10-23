/*
package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/search")
    public List<Course> getCoursesByName(@RequestParam String courseName) {
        //return courseService.getCoursesByName(courseName); Necessário corrigir isto com prioridade com brevidade possível
        return null;
    }

    @PostMapping("/{id}/open-enrollment")
    public Course openEnrollment(@PathVariable Long id) {
        return courseService.openEnrollments(id);
    }

    @PostMapping("/{id}/close-enrollment")
    public Course closeEnrollment(@PathVariable Long id) {
        return courseService.closeEnrollments(id);
    }

    @PostMapping("/{id}/open-course")
    public Course openCourse(@PathVariable Long id) {
        return courseService.openCourse(id);
    }

    @PostMapping("/{id}/close-course")
    public Course closeCourse(@PathVariable Long id) {
        return courseService.closeCourse(id);
    }

}
*/
