package eapli.base.exammanagement.application;

import eapli.base.Course.Domain.Course;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamTime;
import eapli.base.exammanagement.domain.ExamDate;
import eapli.base.exammanagement.domain.ExamTitle;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.sql.Date;
import java.sql.Time;

/**
 * CreateExamController - Criar um Exame
 * 
 * Created by João Cruz
 *
 */

@UseCaseController
public class CreateExamController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private ExamRepository examRepository;

    public void setExamRepository(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam createExam(String examName, Date examDate, Time openTime, Time closeTime, Course course) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        ExamTitle examTitle = new ExamTitle(examName);
        ExamDate examDateObj = new ExamDate(examDate);
        ExamTime examTime = new ExamTime(openTime, closeTime);

        Exam newExam = new Exam(examTitle, examDateObj, examTime, course);
        examRepository.save(newExam); // Salva no repositório do Exame
        return newExam;
    }
}
