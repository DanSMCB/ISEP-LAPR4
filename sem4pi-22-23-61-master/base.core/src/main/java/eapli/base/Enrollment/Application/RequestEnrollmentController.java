package eapli.base.Enrollment.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Enrollment.Domain.Enrollment;
import eapli.base.Enrollment.Domain.EnrollmentDescription;
import eapli.base.Enrollment.Repository.EnrollmentRepository;
import eapli.base.Student_Teacher.Student.Repository.StudentRepository;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;


public class RequestEnrollmentController {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    public RequestEnrollmentController(final EnrollmentRepository enrollmentRepository, final StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
    }

    public void requestEnrollment(Student student, Course course, String description) {
        enrollmentRepository.save(new Enrollment(student,course,new EnrollmentDescription(description)));
    }

    public Student findStudentBySystemUser(SystemUser systemUser) {
        return studentRepository.findStudentBySystemUser(systemUser);
    }
}

