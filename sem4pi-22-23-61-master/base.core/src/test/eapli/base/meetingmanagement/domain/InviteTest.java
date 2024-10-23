package eapli.base.meetingmanagement.domain;

import eapli.base.SharedBoard.domain.PostIt;
import eapli.base.SharedBoard.domain.SharedBoard;
import eapli.base.app.backoffice.console.presentation.meeting.AcceptRefuseInvitesAction;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meetingmanagement.application.AcceptRefuseInvitesController;
import eapli.base.meetingmanagement.application.AcceptRefuseInvitesService;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.PasswordPolicy;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
//import eapli.base.core.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;



public class InviteTest {

    AcceptRefuseInvitesService service = new AcceptRefuseInvitesService(AuthzRegistry.authorizationService(), PersistenceContext.repositories().inviteRepository());

    Date date=new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
    Date time = simpleDateFormat.parse("10:30");


    final SystemUserBuilder userBuilder = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
    final SystemUser newUser = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser newUser2 = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser newUser3 = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser newUser4 = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();

    final SystemUser newUser5 = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT)
            .build();
    Meeting meeting = new Meeting(new MeetingTitle("meeting title"), newUser, new MeetingDuration(30), new MeetingDate(date), new MeetingTime(time));
    Invite invite1 = new Invite(newUser, newUser2, meeting);
    Invite invite2 = new Invite(newUser, newUser3, meeting);

    @Before
    public void setUp() {
        service.acceptInvite(invite1, newUser2);
        service.refuseInvite(invite2);
    }

    public InviteTest() throws ParseException {
    }

    @Test
    void ensureInviteMustHaveSender() {
        System.out.println("must have invite sender");
        assertThrows(IllegalArgumentException.class, () -> new Invite(null, newUser, meeting));
    }

    @Test
    void ensureInviteMustHaveReceiver() {
        System.out.println("must have invite receiver");
        assertThrows(IllegalArgumentException.class, () -> new Invite(newUser, null, meeting));
    }

    @Test
    void ensureInviteMustHaveMeeting() {
        System.out.println("must have meeting");
        assertThrows(IllegalArgumentException.class, () -> new Invite(newUser, newUser2, null));
    }

    @Test
    void EnsureInviteStateChangesToAcceptedWhenUserAccepts(){
        Assertions.assertEquals(invite1.getState(),InviteState.ACCEPTED);
    }

    @Test
    void EnsureMeetingContainsUserWhenInviteIsAccepted(){
        Assertions.assertTrue(meeting.getParticipants().contains(newUser2));
    }

    @Test
    void EnsureInviteStateChangesToRefusedWhenUserRefuses(){
        Assertions.assertEquals(invite2.getState(),InviteState.REJECTED);
    }

    @Test
    void EnsureMeetingDoesNotContainsUserWhenInviteIsRefused(){
        Assertions.assertFalse(meeting.getParticipants().contains(newUser3));
    }
}
