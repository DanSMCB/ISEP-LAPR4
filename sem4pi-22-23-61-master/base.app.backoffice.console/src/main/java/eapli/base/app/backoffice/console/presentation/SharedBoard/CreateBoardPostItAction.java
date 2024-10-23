package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.framework.actions.Action;

public class CreateBoardPostItAction implements Action {

    @Override
    public boolean execute() {
        return new CreateBoardPostItUI().show();
    }
}
