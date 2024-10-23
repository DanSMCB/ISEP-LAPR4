package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.app.backoffice.console.presentation.exam.ListCourseExamsUI;
import eapli.framework.actions.Action;

public class ApproveEnrollmentAction implements Action {
    @Override
    public boolean execute() {
        return new ApproveEnrollmentUI().show();
    }
}
