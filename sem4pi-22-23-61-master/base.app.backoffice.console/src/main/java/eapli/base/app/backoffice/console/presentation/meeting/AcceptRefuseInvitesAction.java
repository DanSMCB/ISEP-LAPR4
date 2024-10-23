package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.framework.actions.Action;

public class AcceptRefuseInvitesAction implements Action {

    @Override
    public boolean execute() {
        return new AcceptRefuseInvitesUI().show();
    }
}
