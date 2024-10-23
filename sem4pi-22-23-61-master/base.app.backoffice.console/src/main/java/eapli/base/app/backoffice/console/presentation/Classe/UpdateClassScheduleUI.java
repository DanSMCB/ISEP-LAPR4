package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.base.Classe.domain.*;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class UpdateClassScheduleUI {


    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final ClassController classController = new ClassController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().classRepository(), PersistenceContext.repositories().teacherRepository());


    public boolean show() throws Exception {
        Teacher teacher = classController.findTeacherBySystemUser(myUser);
           List<Classe> Classes = classController.getAllClasses(teacher.getAcronym());
            for (Classe e : Classes) {
                System.out.println("Class title: " + e.identity() + "; Class start time : " + e.getStartTime() + "; Class end time : " + e.getFinishTime());
            }

        System.out.println("Enter class Title: ");
        Scanner scanner = new Scanner(System.in);
        Classe_Title title = Classe_Title.valueOf(scanner.nextLine());
        scanner.nextLine();

        System.out.println("Enter new class start time (HH:mm): ");
        Classe_Start_Time newStartTime = Classe_Start_Time.valueOf(LocalTime.parse(scanner.nextLine()));

        System.out.println("Enter new class finish time (HH:mm): ");
        Classe_Finish_Time newFinishTime = Classe_Finish_Time.valueOf(LocalTime.parse(scanner.nextLine()));

        classController.updateClassSchedule(title, newStartTime, newFinishTime);

        System.out.println("Class schedule updated successfully.");
        return false;
    }
}


/*
    public class NoClassesException extends Exception {
        public NoClassesException(String message) {
            super(message);
        }
    }

 */
/*
    public void displayAllClasses() throws NoClassesException {
        List<Classe> allClasses = classController.getAllClasses();

        if (allClasses.isEmpty()) {
            throw new NoClassesException("No classes found!");
        }

        for (Classe classe : allClasses) {
            System.out.println(classe);
        }
    }


    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Existing classes: ");
            displayAllClasses();


            System.out.println("Enter class ID: ");
            Long id = scanner.nextLong();

            scanner.nextLine(); // Consume newline left-over

            System.out.println("Enter new class start time (HH:mm): ");
            Classe_Start_Time newStartTime = Classe_Start_Time.valueOf(LocalTime.parse(scanner.nextLine()));

            System.out.println("Enter new class finish time (HH:mm): ");
            Classe_Finish_Time newFinishTime = Classe_Finish_Time.valueOf(LocalTime.parse(scanner.nextLine()));

            classController.updateClassSchedule(id, newStartTime, newFinishTime);

            System.out.println("Class schedule updated successfully.");
            // Display all classes after updating
            System.out.println("Updated classes: ");
            displayAllClasses();

        } catch (NoClassesException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
        }
    }

 */
