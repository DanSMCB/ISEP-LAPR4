package eapli.base.ExtraClasse.aplication;

import eapli.base.Classe.aplication.ClasseDTO;
import eapli.base.Classe.domain.*;
import eapli.base.ExtraClasse.domain.ExtraClasse_Day;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import eapli.base.ExtraClasse.domain.ExtraClasse_Finish_Time;
import eapli.base.ExtraClasse.domain.ExtraClasse_Start_Time;
import eapli.base.ExtraClasse.domain.ExtraClasse_Title;
import eapli.base.ExtraClasse.repository.ExtraClasseRepository;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ExtraClassController {

    private final ExtraClasseRepository repository;
    private TeacherRepository teacherRepository;

    public ExtraClassController(AuthorizationService authorizationService, ExtraClasseRepository repository, TeacherRepository teacherRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
    }


    public ExtraClasseDTO scheduleExtraClass(ExtraClasse_Title title, ExtraClasse_Start_Time startTime, ExtraClasse_Finish_Time finishTime, ExtraClasse_Day day, Acronym teacherAcronym) throws IntegrityViolationException, ConcurrencyException {

        final ExtraClasse newExtraClasse = new ExtraClasse(title, startTime, finishTime, day, teacherAcronym);

        if (checkClassConflict(newExtraClasse)) {
            throw new IllegalStateException("There is a scheduling conflict with this class.");
        } else {
            return toDTO(this.repository.save(newExtraClasse));
        }
    }

    public boolean checkClassConflict(ExtraClasse classeToCheck) {
        Iterable<ExtraClasse> allExtraClasses = repository.findAll();
        for (ExtraClasse existingClass : allExtraClasses) {
            if (existingClass.getTitle().equals(classeToCheck.getTitle())) {
                // Skip checking against the same class
                continue;
            }

            // Only compare classes on the same day
            if (existingClass.getDay().equals(classeToCheck.getDay())) {
                // Check for time overlap
                if (isTimeOverlapUserInput(classeToCheck.getStart_time(), classeToCheck.getFinish_time(), existingClass)) {
                    return true; // Conflict found
                }
            }
        }

        return false; // No conflict found
    }

    public List<Teacher> findTeacherByAcronym(Acronym teacherAcronym){
        return teacherRepository.findTeacherByAcronym(teacherAcronym);
    }

    public boolean isTimeOverlapUserInput(ExtraClasse_Start_Time userInputStartTime, ExtraClasse_Finish_Time userInputEndTime, ExtraClasse existingClass) {
        // Extract the LocalTime from userInputStartTime and userInputEndTime
        LocalTime userInputStart = userInputStartTime.getStart_time();
        LocalTime userInputEnd = userInputEndTime.getFinish_time();

        // Convert start and end times of user input to minutes since midnight
        int userInputStartMinutes = userInputStart.getHour() * 60 + userInputStart.getMinute();
        int userInputEndMinutes = userInputEnd.getHour() * 60 + userInputEnd.getMinute();

        // Extract the LocalTime from existingClass's start and end time
        LocalTime existingStartTime = existingClass.getStart_time().getStart_time();
        LocalTime existingFinishTime = existingClass.getFinish_time().getFinish_time();

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
    public ExtraClasseDTO toDTO(ExtraClasse extraClasse) {
        return new ExtraClasseDTO(
                extraClasse.getTitle().getTitle(),
                extraClasse.getStart_time().getStart_time(),
                extraClasse.getFinish_time().getFinish_time(),
                extraClasse.getDay().getDay(),
                extraClasse.getAcronym().getValue()
        );
    }
}
