package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.framework.visitor.Visitor;

public class InvitePrinter implements Visitor<Invite> {

    @Override
    public void visit(Invite visitee) {
        System.out.printf(visitee.toString());
    }
}
