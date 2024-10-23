package eapli.base.app.backoffice.console.presentation.exam;

import eapli.framework.actions.Action;

public class CreateExamAction implements Action {

    @Override
    public boolean execute() {
        return new CreateExamUI().show();
    }
}