package eapli.base.app.backoffice.console.presentation.exam;

import eapli.base.Course.Domain.Course;
import eapli.base.app.backoffice.console.presentation.course.CoursePrinter;
import eapli.base.exammanagement.application.ListCourseExamsController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Iterator;

public class ListCourseExamsUI extends AbstractUI {

    private final ListCourseExamsController controller = new ListCourseExamsController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().examRepository(), PersistenceContext.repositories().courseRepository());

    @Override
    public String headline() {
        return "List all exams in a course";
    }

    @Override
    protected boolean doShow() {
        final Iterable<Course> courses = this.controller.listCourses();
        final SelectWidget<Course> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
        selector.show();
        final Course course = selector.selectedElement();
        if (course != null) {
            Iterable<Exam> courseExams = controller.listCourseExams(course);
            for(Exam e : courseExams){
                System.out.println("Exam id: " + e.identity() + "; exam course: " + e.getExamCourse().getCourseName());
            }
        }
        return false;
    }


}
