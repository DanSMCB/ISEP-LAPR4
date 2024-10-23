package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.Course.Application.ListCoursesController;
import eapli.base.Course.Domain.Course;
import eapli.base.Enrollment.Application.RequestEnrollmentController;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.app.backoffice.console.presentation.course.CoursePrinter;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class RequestEnrollmentUI extends AbstractUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private RequestEnrollmentController controller = new RequestEnrollmentController(PersistenceContext.repositories().enrollmentRepository(), PersistenceContext.repositories().studentRepository());
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();
    private final ListCoursesController listCoursesController = new ListCoursesController(PersistenceContext.repositories().courseRepository());


    @Override
    protected boolean doShow() {
        Student student = controller.findStudentBySystemUser(myUser);

        final Iterable<Course> courses = this.listCoursesController.listCourses();
        final SelectWidget<Course> selector = new SelectWidget<>("Courses:", courses, new CoursePrinter());
        selector.show();
        final Course choosenCourse = selector.selectedElement();

        String description = Console.readLine("What is the Enrollment description:");
        controller.requestEnrollment(student, choosenCourse, description);
        return false;
    }

    @Override
    public String headline() {
        return "--Request Enrollment UI--";
    }
}

