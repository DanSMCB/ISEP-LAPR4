package eapli.base.Course.Repository;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Domain.Course_ID;
import eapli.base.Course.Domain.Course_Name;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.repositories.DomainRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public interface CourseRepository extends DomainRepository<Course_Name,Course> {

     Iterable<Course> findCourseByName(String courseName);


     /*default Course openCourse(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setIsOpen(true);
          return this.save(course);
     }
     default Course closeCourse(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setIsOpen(false);
          return this.save(course);
     }

     default Course openEnrollments(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setEnrollmentOpen(true);
          return this.save(course);
     }

     default Course closeEnrollments(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setEnrollmentOpen(false);
          return this.save(course);
     }*/
}
