package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.base.Classe.domain.*;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScheduleClassUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserSession session = authz.session().orElseThrow(IllegalStateException::new);
    private final SystemUser authenticatedUser = session.authenticatedUser();

    private final ClassController classController = new ClassController(
            AuthzRegistry.authorizationService(),
            PersistenceContext.repositories().classRepository(),
            PersistenceContext.repositories().teacherRepository());

    public boolean show() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter class title: ");
            Classe_Title title = Classe_Title.valueOf(scanner.nextLine());

            System.out.println("Enter class start time (HH:mm): ");
            LocalTime startTime = LocalTime.parse(scanner.nextLine());
            Classe_Start_Time userInputStartTime = new Classe_Start_Time(startTime);

            System.out.println("Enter class finish time (HH:mm): ");
            LocalTime endTime = LocalTime.parse(scanner.nextLine());
            Classe_Finish_Time userInputEndTime = new Classe_Finish_Time(endTime);

            System.out.println("Enter class start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            Classe_Start_Date start_date = new Classe_Start_Date(startDate);

            System.out.println("Enter class finish date (YYYY-MM-DD): ");
            LocalDate finishDate = LocalDate.parse(scanner.nextLine());
            Classe_Finish_Date finish_date = new Classe_Finish_Date(finishDate);

            System.out.println("Enter day of the week (1-7): ");
            int dayOfWeek = scanner.nextInt();
            DayOfWeek userInputDayOfWeek = DayOfWeek.valueOf(dayOfWeek);

            scanner.nextLine(); // Consume the newline character

            System.out.println("Enter teacher acronym: ");
            Acronym teacherAcronym = Acronym.valueOf(scanner.nextLine());

            TeacherRepository teacherRepository = PersistenceContext.repositories().teacherRepository();
            List<Teacher> teacherOptional = teacherRepository.findTeacherByAcronym(teacherAcronym);
            if (teacherOptional.isEmpty()) {
                throw new IllegalArgumentException("No teacher found with the provided acronym");
            }

            boolean hasConflict = classController.checkClassConflict(new Classe(
                    title, start_date, finish_date, userInputStartTime, userInputEndTime, userInputDayOfWeek, teacherAcronym));
            if (hasConflict) {
                System.out.println("Class conflicts with an existing class.");
                // Handle class conflict, display an error message or take any appropriate action
            } else {
                // Proceed with scheduling the class
                classController.scheduleClass(
                        title, userInputStartTime, userInputEndTime, start_date, finish_date, userInputDayOfWeek, teacherAcronym);
                System.out.println("Class scheduled successfully.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

