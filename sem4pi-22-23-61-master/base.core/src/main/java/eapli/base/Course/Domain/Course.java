package eapli.base.Course.Domain;

import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COURSE")
public class Course implements AggregateRoot<Course_Name> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(unique = true)
    private Course_Name courseName;

    private Maximum_Number_Of_Students maximumNumberOfStudents;
    private Minimum_Number_Of_Students minimumNumberOfStudents;
    private Small_Textual_Description smallTextualDescription;

    private boolean enrollmentOpen;
    private boolean isOpen;


    @ManyToOne
    @JoinColumn(name = "teacher_in_charge_id")
    private Teacher teacherInCharge;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    public Course() {
    }

    public void addStudent(Student student) {
        students.add(student);
    }


    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Course_Name identity() {
        return courseName;
    }


    public Course(Course_Name courseName, Maximum_Number_Of_Students maximumNumberOfStudents, Minimum_Number_Of_Students minimumNumberOfStudents, Small_Textual_Description smallTextualDescription, Teacher teacher){
        this.courseName=courseName;
        this.maximumNumberOfStudents=maximumNumberOfStudents;
        this.minimumNumberOfStudents=minimumNumberOfStudents;
        this.smallTextualDescription=smallTextualDescription;
        this.teacherInCharge=teacher;
        this.students=new HashSet<>();

        this.isOpen = false;
        this.enrollmentOpen = false;
    }

    public Course_Name getCourseName() {
        return courseName;
    }

    public Maximum_Number_Of_Students getMaximumNumberOfStudents() {
        return maximumNumberOfStudents;
    }

    public Minimum_Number_Of_Students getMinimumNumberOfStudents() {
        return minimumNumberOfStudents;
    }

    public Small_Textual_Description getSmallTextualDescription() {
        return smallTextualDescription;
    }

    public Teacher getTeacherInCharge() {
        return teacherInCharge;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setCourseName(Course_Name courseName) {
        this.courseName = courseName;
    }

    public void setMaximumNumberOfStudents(Maximum_Number_Of_Students maximumNumberOfStudents) {
        this.maximumNumberOfStudents = maximumNumberOfStudents;
    }

    public void setMinimumNumberOfStudents(Minimum_Number_Of_Students minimumNumberOfStudents) {
        this.minimumNumberOfStudents = minimumNumberOfStudents;
    }

    public void setSmallTextualDescription(Small_Textual_Description smallTextualDescription) {
        this.smallTextualDescription = smallTextualDescription;
    }

    public void setEnrollmentOpen(boolean enrollmentOpen) {
        this.enrollmentOpen = enrollmentOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setTeacherInCharge(Teacher teacherInCharge) {
        this.teacherInCharge = teacherInCharge;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}

