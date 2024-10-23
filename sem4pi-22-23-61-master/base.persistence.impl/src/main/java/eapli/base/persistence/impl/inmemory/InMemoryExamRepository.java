package eapli.base.persistence.impl.inmemory;

import eapli.base.Course.Domain.Course;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryExamRepository extends InMemoryDomainRepository<Exam, Long> implements ExamRepository {
    @Override
    public boolean contains(Exam exam) {
        return super.contains(exam);
    }

    @Override
    public Exam save(Exam entity) {
        return super.save(entity);
    }


    @Override
    public Iterable<Exam> findAll() {
        return super.findAll();
    }

    public List<Exam> findByTitle(String title) {
        List<Exam> exams = new ArrayList<>();
        for (Exam exam : findAll()) {
            if (exam.getExamTitle().toString().equalsIgnoreCase(title)) {
                exams.add(exam);
            }
        }
        return exams;
    }

    @Override
    public List<Exam> findStudentFutureExams(Student student) {
        return null;
    }

    @Override
    public Iterable<Exam> findByCourse(Course course) {
        return null;
    }
}
