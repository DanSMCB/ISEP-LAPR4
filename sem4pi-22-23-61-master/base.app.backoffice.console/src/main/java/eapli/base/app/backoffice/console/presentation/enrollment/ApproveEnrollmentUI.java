package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.Enrollment.Application.ApproveEnrollmentController;
import eapli.base.Enrollment.Application.ListEnrollmentsController;
import eapli.base.Enrollment.Domain.Enrollment;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;



public class ApproveEnrollmentUI extends AbstractUI {

    private final ListEnrollmentsController listEnrollmentsController = new ListEnrollmentsController(PersistenceContext.repositories().enrollmentRepository());

    private final ApproveEnrollmentController controller = new ApproveEnrollmentController(PersistenceContext.repositories().enrollmentRepository());


    @Override
    protected boolean doShow() {

        final Iterable<Enrollment> enrollments = this.listEnrollmentsController.listEnrollments();
        final SelectWidget<Enrollment> selector = new SelectWidget<>("Enrollments:", enrollments, new EnrollmentPrinter());
        selector.show();
        final Enrollment chosenEnrollment = selector.selectedElement();
        int isToBeAccepted = Console.readInteger("Do you want to accept this enrollment?(1-Accept/0-Reject)");
        controller.approveEnrollment(chosenEnrollment,isToBeAccepted);
        return false;
    }

    @Override
    public String headline() {
        return "--Approve Enrollment UI--";
    }
}
