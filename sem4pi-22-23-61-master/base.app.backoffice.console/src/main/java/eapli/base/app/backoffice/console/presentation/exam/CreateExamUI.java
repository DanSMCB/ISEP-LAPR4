package eapli.base.app.backoffice.console.presentation.exam;

import eapli.base.exammanagement.application.CreateExamController;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.springframework.expression.ParseException;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateExamUI extends AbstractUI {

    private final CreateExamController controller = new CreateExamController();

    @Override
    protected boolean doShow() {
        final String examTitle = Console.readLine("Exam Title");
        final Date examDate = Console.readDate("Exam Date (YYYY/MM/DD)", "YYYY/MM/dd");
        final Time openTime = readTime("Open Time (HH:mm)");
        final Time closeTime = readTime("Close Time (HH:mm)");

        System.out.println("\n\nSYSTEM: It seems that the create a Course system is not fully implemented, we will skip this part, it will introduced as 'null', sorry.\n\n");

        Exam newExam = controller.createExam(examTitle, java.sql.Date.valueOf(String.valueOf(examDate)), Time.valueOf(String.valueOf(openTime)), Time.valueOf(String.valueOf(closeTime)), null);
        if (newExam != null) {
            System.out.println("Exam created successfully:");
            System.out.println(newExam);
        } else {
            System.out.println("Failed to create exam.");
        }

        return false;
    }

    private static Time readTime(String message) {
        String format = "HH:mm";
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false); // Disallow lenient parsing

        while (true) {
            try {
                String input = Console.readLine(message);
                Date parsedDate = dateFormat.parse(input);
                return new Time(parsedDate.getTime());
            } catch (ParseException | java.text.ParseException e) {
                System.out.println("\nInvalid time format. Please enter the time in the format: " + format + " (Hours : Minutes)");
                System.out.println("Example: 10:00\n");
            }
        }
    }

    @Override
    public String headline() {
        return "Create Exam";
    }
}
