package eapli.base.app.backoffice.console.presentation.course;

import eapli.base.Course.Application.CreateCourseController;
import eapli.base.Course.Application.OpenCloseEnrollementsController;
import eapli.base.Course.Domain.Course;
import eapli.base.Course.Domain.Course_ID;
import eapli.base.Course.Domain.Course_Name;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.application.ListFutureExamsController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Scanner;

public class OpenCloseEnrollementsUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final OpenCloseEnrollementsController controller = new OpenCloseEnrollementsController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courseRepository());


    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);

        String courseName = Console.readLine("Digite o nome do curso para abrir ou fechar as inscrições: ");
        Course_Name courseName1 = new Course_Name(courseName);

        System.out.print("Digite 'abrir' para abrir as inscrições ou 'fechar' para fechá-las: ");
        String option = scanner.next();

        if (option.equalsIgnoreCase("abrir")) {
            controller.openEnrollments(courseName1);
            System.out.println("As inscrições para o curso " + courseName + " foram abertas com sucesso.");
        } else if (option.equalsIgnoreCase("fechar")) {
            controller.closeEnrollments(courseName1);
            System.out.println("As inscrições para o curso " + courseName + " foram fechadas com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Open/Close enrollements";
    }

}
