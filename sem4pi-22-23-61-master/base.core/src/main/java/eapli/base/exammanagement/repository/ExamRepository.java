package eapli.base.exammanagement.repository;

import eapli.base.Course.Domain.Course;
import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.repositories.DomainRepository;

public interface ExamRepository extends DomainRepository<Long, Exam> {

    Iterable<Exam> findStudentFutureExams(Student student);

    Iterable<Exam> findByCourse(Course course);
}