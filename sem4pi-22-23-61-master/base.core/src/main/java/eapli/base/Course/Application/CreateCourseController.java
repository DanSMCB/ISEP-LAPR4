package eapli.base.Course.Application;

import eapli.base.Course.Domain.*;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;

public class CreateCourseController {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CreateCourseController(final CourseRepository courseRepository, final TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository=teacherRepository;
    }

    public void createCourse(String name, int min, int max, String description, Teacher teacher) {
        courseRepository.save(new Course(new Course_Name(name),new Maximum_Number_Of_Students(max),new Minimum_Number_Of_Students(min),new Small_Textual_Description(description), teacher));
    }

    public Iterable<Teacher> listTeachers(){
        return this.teacherRepository.findAll();
    }
}
