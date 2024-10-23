package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.framework.actions.Action;


public class SharedBoardAction implements Action {

    @Override
    public boolean execute() {
        return new SharedBoardUI().show();
    }
}
