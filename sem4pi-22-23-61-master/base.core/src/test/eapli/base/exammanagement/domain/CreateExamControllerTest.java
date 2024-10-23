package eapli.base.exammanagement.domain;

import eapli.base.Course.Domain.Course;
import eapli.base.exammanagement.application.CreateExamController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.persistence.impl.inmemory.InMemoryExamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateExamControllerTest {
    private CreateExamController createExamController;
    private ExamRepository examRepository;

    @BeforeEach
    void setUp() {
        examRepository = new InMemoryExamRepository(); // InMemoryExamRepository para testar
        createExamController = new CreateExamController();
        createExamController.setExamRepository(examRepository);
    }

    @Test
    void createExamValidDataReturnsNewExam() {
        // Arrange
        String examName = "Exame Fisica";
        Date examDate = Date.valueOf("2023/06/18");
        Time openTime = Time.valueOf("09:00");
        Time closeTime = Time.valueOf("12:00");
        Course course = new Course();

        // Act
        Exam result = createExamController.createExam(examName, examDate, openTime, closeTime, course);

        // Assert
        assertTrue(examRepository.contains(result)); // Verifica se guardou no ExamRepository
        assertEquals(examName, result.getExamTitle().toString());
        assertEquals(examDate.toString(), result.getExamDate().toString());
        assertEquals(openTime.toString(), result.getExamTime().getOpenDate().toString());
        assertEquals(closeTime.toString(), result.getExamTime().getCloseDate().toString());
    }
}
