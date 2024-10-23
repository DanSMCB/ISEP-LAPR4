package eapli.base.app.backoffice.console.presentation.course;

import eapli.base.Course.Application.OpenCloseCourseController;
import eapli.base.Course.Application.OpenCloseEnrollementsController;
import eapli.base.Course.Domain.Course_ID;
import eapli.base.Course.Domain.Course_Name;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.sql.SQLOutput;
import java.util.Scanner;

public class OpenCloseCourseUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final OpenCloseCourseController controller = new OpenCloseCourseController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().courseRepository());


    @Override
    protected boolean doShow() {
        Scanner scanner = new Scanner(System.in);

        String courseName = Console.readLine("Digite o nome do curso que deseja abrir ou fechar:");
        Course_Name courseName1 = new Course_Name(courseName);

        System.out.println("Digite 'abrir' para abrir ou 'fechar' para fechar o curso");
        String option = scanner.next();

        if (option.equalsIgnoreCase("abrir")) {
            controller.openCourse(courseName1);
            System.out.println("O curso " + courseName + " foi aberto com sucesso.");
        } else if (option.equalsIgnoreCase("fechar")) {
            controller.closeCourse(courseName1);
            System.out.println("O curso " + courseName + " foi fechado com sucesso.");
        } else {
            System.out.println("Opção inválida.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Open/Close Course";
    }

}
