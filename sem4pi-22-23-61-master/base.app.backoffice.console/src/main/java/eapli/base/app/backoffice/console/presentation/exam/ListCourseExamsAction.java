package eapli.base.app.backoffice.console.presentation.exam;

import eapli.framework.actions.Action;

public class ListCourseExamsAction implements Action {

    @Override
    public boolean execute() {
        return new ListCourseExamsUI().show();
    }
}
