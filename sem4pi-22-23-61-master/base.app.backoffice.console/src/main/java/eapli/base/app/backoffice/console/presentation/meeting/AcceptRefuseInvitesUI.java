package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meetingmanagement.application.AcceptRefuseInvitesController;
import eapli.base.meetingmanagement.domain.Invite;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AcceptRefuseInvitesUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final AcceptRefuseInvitesController controller = new AcceptRefuseInvitesController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().inviteRepository());

    @Override
    protected boolean doShow() {
        int option;
        do {
            final Iterable<Invite> invites = this.controller.invitesReceived(myUser.username());
            final SelectWidget<Invite> selector = new SelectWidget<>("Invites:", invites, new InvitePrinter());
            selector.show();
            final Invite invite = selector.selectedElement();
            if (invite != null) {
                System.out.println("Type \"1\" to accept the meeting invite or \"0\" to refuse it.");
                int option2 = Console.readOption(0, 1, 0);
                if (option2 == 1){
                    controller.acceptInvite(invite, myUser);
                    System.out.println("Meeting invite accepted.");
                }
                if (option2 == 0){
                    controller.refuseInvite(invite);
                    System.out.println("Meeting invite refused");
                }
            }

            System.out.println("Type \"1\" to accept or refuse another meeting or \"0\" to leave.");
            option = Console.readOption(0, 1, 0);
        } while (option != 0);

        return false;
    }

    @Override
    public String headline() {
        return "Accept/Refuse meeting invites";
    }
}
