package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.domain.InviteState;
import eapli.base.meetingmanagement.domain.Meeting;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class AcceptRefuseInvitesService {

    private final AuthorizationService authz;
    private final InviteRepository inviteRepository;

    public AcceptRefuseInvitesService(final AuthorizationService authz, final InviteRepository inviteRepository) {
        this.authz = authz;
        this.inviteRepository = inviteRepository;
    }

    /**
     * @return
     */
    public Iterable<Invite> invitesReceived(Username username) {
        return inviteRepository.findInvitesByReceiverUsername(username);
    }

    public void acceptInvite(Invite invite, SystemUser systemUser){
        invite.addMeetingParticipants(systemUser);
        invite.setState(InviteState.ACCEPTED);
    }

    public void refuseInvite(Invite invite){
        invite.setState(InviteState.REJECTED);
    }
}