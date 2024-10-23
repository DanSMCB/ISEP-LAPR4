package eapli.base.app.backoffice.console.presentation.course;

import eapli.base.app.backoffice.console.presentation.exam.ListCourseExamsUI;
import eapli.framework.actions.Action;
import javax.swing.*;

public class OpenCloseEnrollementsAction implements Action {
    @Override
    public boolean execute() {
        return new OpenCloseEnrollementsUI().show();
    }
}
