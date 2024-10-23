package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.base.SharedBoard.aplication.SharedBoardArchiveController;
import eapli.base.SharedBoard.aplication.SharedBoardController;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import javax.swing.*;
import eapli.framework.actions.Action;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class SharedBoardArchiveAction implements Action{
    @Override
    public boolean execute() {
        return new SharedBoardArchiveUI().show();
    }

}
