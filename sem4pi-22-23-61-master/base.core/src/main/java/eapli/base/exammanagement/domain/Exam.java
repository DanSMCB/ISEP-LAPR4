package eapli.base.exammanagement.domain;

import eapli.base.Course.Domain.Course;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.String;

@Entity
@Table(name = "EXAM")
public class Exam implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDEXAM")
    private Long id;

    @Embedded
    private ExamTitle examTitle;

    @Embedded
    private ExamResult result;

    @Embedded
    private ExamTime examTime;

    @Embedded
    private ExamDate examDate;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course examCourse;

    public Exam(ExamTitle examTitle, ExamDate examDate, ExamTime examTime, Course course) {
        Preconditions.noneNull(examTitle, examDate, course);

        this.examTitle = examTitle;
        this.examDate = examDate;
        this.examTime = examTime;
        this.examCourse = course;
        this.result = null;
    }

    protected Exam(){}

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        Exam exam = (Exam) other;
        return this.equals(exam) && equals(exam.getExamCourse()) && getExamDate().equals(exam.getExamDate()) && getExamTime().equals(exam.getExamTime()) && getResult().equals(exam.getResult()) && getExamTitle().equals(exam.getExamTitle());
    }

    public ExamTitle getExamTitle() {
        return this.examTitle;
    }

    public Course getExamCourse() {
        return this.examCourse;
    }

    public ExamDate getExamDate() {
        return this.examDate;
    }

    public ExamTime getExamTime() {
        return this.examTime;
    }

    public ExamResult getResult() {
        return this.result;
    }

    public void setResult(ExamResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public Long identity() {
        return this.id;
    }
}