package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.base.ExtraClasse.aplication.ExtraClassController;
import eapli.framework.actions.Action;

public class ScheduleExtraClassAction implements Action {



    @Override
    public boolean execute() {
        try {
            return new ScheduleExtraClassUI().show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

    /*
    private final ExtraClassController extraClassController;

    public ScheduleExtraClassAction(ExtraClassController extraClassController) {
        this.extraClassController = extraClassController;
    }

    public boolean execute(){
        try {
            new ScheduleExtraClassUI(extraClassController).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true; // Return a boolean indicating if the operation was successful.
    }


     */

