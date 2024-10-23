package eapli.base.Enrollment.Domain;

import eapli.base.Course.Domain.Course;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Enrollment implements AggregateRoot<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EnrollmentID", nullable = false)
    private Long id;

    @Column(name = "EnrollmentStudent", nullable = false)
    private Student student;

    @Column(name = "EnrollmentCourse", nullable = false)
    private Course course;

    @Column(name = "Description", nullable = false)
    private EnrollmentDescription description;

    @Column(name="EnrollmentState", nullable = false)
    private EnrollmentState state;

    protected Enrollment(){}


    public Enrollment(Student student, Course course, EnrollmentDescription description) {
        this.student = student;
        this.course = course;
        this.description = description;
        this.state = EnrollmentState.PENDING;//valor default(Só fica aceite a mando do manager)
    }


    public void acceptEnrollment() {
        this.state = EnrollmentState.ACCEPTED;
    }

    public void rejectEnrollment() {
        this.state = EnrollmentState.REJECTED;
    }



    @Override
    public Long identity() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public EnrollmentDescription getDescription() {
        return description;
    }

    public void setDescription(EnrollmentDescription description) {
        this.description = description;
    }

    public EnrollmentState getState() {
        return state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

}
