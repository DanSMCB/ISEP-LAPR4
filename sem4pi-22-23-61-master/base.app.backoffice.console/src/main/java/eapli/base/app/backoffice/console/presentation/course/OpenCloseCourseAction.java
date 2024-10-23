package eapli.base.app.backoffice.console.presentation.course;

import javax.swing.*;
import eapli.framework.actions.Action;

public class OpenCloseCourseAction implements Action {

    @Override
    public boolean execute() {
        return new OpenCloseCourseUI().show();
    }
}
