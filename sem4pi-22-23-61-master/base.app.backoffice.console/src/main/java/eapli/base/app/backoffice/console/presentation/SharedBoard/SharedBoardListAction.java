package eapli.base.app.backoffice.console.presentation.SharedBoard;


import eapli.framework.actions.Action;


public class SharedBoardListAction implements Action {
    @Override
    public boolean execute() {
        return new SharedBoardListUI().show();
    }
}

