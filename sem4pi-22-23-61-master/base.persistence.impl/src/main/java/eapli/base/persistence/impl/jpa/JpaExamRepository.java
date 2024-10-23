package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Course.Domain.Course;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.Student_Teacher.Student.domain.MechanographicNumber;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaBaseRepository;

import javax.persistence.*;
import java.util.List;

class JpaExamRepository extends JpaBaseRepository<Exam, Long, Long> implements ExamRepository {

    public JpaExamRepository() {
        super("id");
    }

    @Override
    public Iterable<Exam> findByCourse(final Course course) {
        final TypedQuery<Exam> q = entityManager().createQuery(
                "SELECT e FROM Exam e WHERE e.examCourse.courseName.value = :course", Exam.class);
        q.setParameter("course", course.getCourseName().getValue());
        return q.getResultList();
    }

    @Override
    public Iterable<Exam> findStudentFutureExams(final Student student) {
        final TypedQuery<Exam> q = entityManager().createQuery(
                "SELECT e FROM Exam e WHERE :student MEMBER OF e.examCourse.students AND e.examDate.examDate > CURRENT_DATE", Exam.class);
        q.setParameter("student", student);
        return q.getResultList();
    }
}
