package eapli.base.Enrollment.Domain.BulkEnrollment;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.Enrollment.Application.RequestEnrollmentController;
import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.framework.domain.repositories.IntegrityViolationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BulkEnrollment {
    private final RequestEnrollmentController requestEnrollmentController;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public BulkEnrollment(RequestEnrollmentController requestEnrollmentController, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.requestEnrollmentController = requestEnrollmentController;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public void bulkEnrollmentReadFile(String csvFilePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                try {
                    Student student = studentRepository.findStudentByMechanographicNumber(data[0].trim());
                    Iterable<Course> it = courseRepository.findCourseByName(data[1].trim());
                    Course course = it.iterator().next();
                    String description = data[2].trim();
                    try {
                        requestEnrollmentController.requestEnrollment(student, course, description);
                    } catch (final IntegrityViolationException e) {
                        System.out.println("That enrollment is invalid.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
