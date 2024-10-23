package eapli.base.exammanagement.domain;

import eapli.base.Course.Domain.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExamTest {

    Date date=new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
    Date time = simpleDateFormat.parse("10:30");
    Date time2 = simpleDateFormat.parse("11:30");

    public ExamTest() throws ParseException {
    }

    @Test
    void ensureExamMustHaveTime() {
        System.out.println("must have exam time");

        assertThrows(IllegalArgumentException.class, () -> new Exam(null, new ExamDate(date), new Course(new Course_Name("INF"), new Maximum_Number_Of_Students(100), new Minimum_Number_Of_Students(10), new Small_Textual_Description("Desc"))));
    }

    @Test
    void ensureExamMustHaveDate() {
        System.out.println("must have exam date");

        assertThrows(IllegalArgumentException.class, () -> new Exam(new ExamTime(time,time2), null, new Course(new Course_Name("INF"), new Maximum_Number_Of_Students(100), new Minimum_Number_Of_Students(10), new Small_Textual_Description("Desc"))));
    }

    @Test
    void ensureExamMustHaveCourse() {
        System.out.println("must have exam course");

        assertThrows(IllegalArgumentException.class, () -> new Exam(new ExamTime(time,time2), new ExamDate(date), null));
    }

    @Test
    void ensureExamCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Exam());
    }
}
