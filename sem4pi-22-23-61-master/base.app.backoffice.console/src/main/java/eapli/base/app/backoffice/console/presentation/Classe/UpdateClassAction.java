package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.framework.actions.Action;

public class UpdateClassAction implements Action {
    @Override
    public boolean execute() {
        try {
            return new UpdateClassScheduleUI().show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
