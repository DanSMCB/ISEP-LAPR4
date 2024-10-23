package eapli.base.Classe.aplication;

import eapli.base.Classe.domain.*;
import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ClassController {


    private ClassRepository repository;
    private final ScheduleClassService scs;

    public ClassController(final AuthorizationService authz, final ClassRepository repository, final TeacherRepository teacherRepository) {
        scs = new ScheduleClassService(authz, repository, teacherRepository);

    }

    public ClasseDTO scheduleClass(Classe_Title title, Classe_Start_Time startTime, Classe_Finish_Time finishTime, Classe_Start_Date start_date, Classe_Finish_Date finishDate, DayOfWeek day, Acronym teacherAcronym) throws IntegrityViolationException, ConcurrencyException {


        final Classe newClasse = new Classe(title, start_date, finishDate, startTime, finishTime, day, teacherAcronym);

        if (checkClassConflict(newClasse)) {
            throw new IllegalStateException("There is a scheduling conflict with this class.");
        } else {
            return toDTO(this.repository.save(newClasse));
        }
    }


    public Teacher findTeacherBySystemUser(SystemUser systemUser) {
        return scs.findTeacherBySystemUser(systemUser);
    }

    public boolean checkClassConflict(Classe classeToCheck) {
        List<Classe> allClasses = (List<Classe>) repository.findAll();
        for (Classe existingClass : allClasses) {
            if (existingClass.getTitle().equals(classeToCheck.getTitle())) {
                // Skip checking against the same class
                continue;
            }

            // Only compare classes on the same date
            if (existingClass.getStartDate().equals(classeToCheck.getStartDate())) {
                // Check for time overlap
                if (isTimeOverlapUserInput(classeToCheck.getStartTime(), classeToCheck.getFinishTime(), existingClass)) {
                    return true; // Conflict found
                }
            }
        }

        return false; // No conflict found
    }

    /**
     * Checks if the class specified by userInputStartTime and userInputEndTime overlaps
     * with the existing class's start and end times.
     *
     * @param userInputStartTime The start time of the class to check.
     * @param userInputEndTime   The end time of the class to check.
     * @param existingClass      The class with which to compare the given class.
     * @return True if the given class overlaps with the existing class, false otherwise.
     */
    public boolean isTimeOverlapUserInput(Classe_Start_Time userInputStartTime, Classe_Finish_Time userInputEndTime, Classe existingClass) {
        // Extract the LocalTime from userInputStartTime and userInputEndTime
        LocalTime userInputStart = userInputStartTime.getStartTime();
        LocalTime userInputEnd = userInputEndTime.getFinish_time();

        // Convert start and end times of user input to minutes since midnight
        int userInputStartMinutes = userInputStart.getHour() * 60 + userInputStart.getMinute();
        int userInputEndMinutes = userInputEnd.getHour() * 60 + userInputEnd.getMinute();

        // Extract the LocalTime from existingClass's start and end time
        LocalTime existingStartTime = existingClass.getStartTime().getStartTime();
        LocalTime existingFinishTime = existingClass.getFinishTime().getFinish_time();

        // Convert start and end times of existing class to minutes since midnight
        int existingStartMinutes = existingStartTime.getHour() * 60 + existingStartTime.getMinute();
        int existingEndMinutes = existingFinishTime.getHour() * 60 + existingFinishTime.getMinute();

        // Check for overlap: does user input class start during existing class?
        if (userInputStartMinutes >= existingStartMinutes && userInputStartMinutes < existingEndMinutes) {
            return true; // Overlap found
        }

        // Check for overlap: does existing class start during user input class?
        if (existingStartMinutes >= userInputStartMinutes && existingStartMinutes < userInputEndMinutes) {
            return true; // Overlap found
        }

        // If neither condition above is met, there is no overlap
        return false; // No overlap found
    }


    public Classe updateClassSchedule(Classe_Title title,Classe_Start_Time newStartTime, Classe_Finish_Time newFinishTime) throws IntegrityViolationException, ConcurrencyException {

        Optional<Classe> optionalClass = this.repository.ofIdentity(title);
        if (optionalClass.isPresent()) {
            Classe classToUpdate = optionalClass.get();
            classToUpdate.changeStartTime(newStartTime);
            classToUpdate.changeFinishTime(newFinishTime);
            return this.repository.save(classToUpdate);
        } else {
            throw new IllegalArgumentException("No class found with title: " + title);
        }
    }

    public List<Classe> getAllClasses(Acronym acronym) {
        return repository.findClassesByTeacher(String.valueOf(acronym));
    }



    public ClasseDTO toDTO(Classe classe) {
        return new ClasseDTO(
                classe.getTitle().getTitle(),
                classe.getStartTime().getStartTime(),
                classe.getFinishTime().getFinish_time(),
                classe.getStartDate().getStart_date(),
                classe.getFinishDate().getFinish_date(),
                classe.getDay().getDay(),
                classe.getAcronym().getValue()
        );
    }
}




