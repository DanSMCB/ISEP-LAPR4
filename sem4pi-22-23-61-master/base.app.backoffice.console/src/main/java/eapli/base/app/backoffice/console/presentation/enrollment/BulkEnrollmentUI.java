package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.Enrollment.Application.BulkEnrollmentController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class BulkEnrollmentUI extends AbstractUI {
    private final BulkEnrollmentController bulkEnrollmentController = new BulkEnrollmentController(PersistenceContext.repositories().enrollmentRepository());

    @Override
    protected boolean doShow() {
        String csvPath = Console.readLine("Please, indicate the CSV file path which you pretend to bulk Enroll:");
        bulkEnrollmentController.bulkEnrollment(csvPath);
        return false;
    }

    @Override
    public String headline() {
        return "--Bulk Enrollment UI--";
    }
}
