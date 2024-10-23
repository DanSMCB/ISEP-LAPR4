package eapli.base.app.backoffice.console.presentation.SharedBoard;

import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.framework.visitor.Visitor;

public class SharedBoardPrinter implements Visitor<SharedBoard> {

    @Override
    public void visit(SharedBoard visitee) {
        System.out.printf(visitee.getTitle().getTitle());
    }
}
