package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class AcceptRefuseInvitesController {

    private final AcceptRefuseInvitesService svc;

    public AcceptRefuseInvitesController(final AuthorizationService authz, final InviteRepository inviteRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        svc = new AcceptRefuseInvitesService(authz, inviteRepository);
    }

    /**
     * List all exams.
     *
     * @return
     */
    public Iterable<Invite> invitesReceived(Username username) {
        return svc.invitesReceived(username);
    }

    public void acceptInvite(Invite invite, SystemUser systemUser){
        svc.acceptInvite(invite, systemUser);
    }

    public void refuseInvite(Invite invite){
        svc.refuseInvite(invite);
    }
}