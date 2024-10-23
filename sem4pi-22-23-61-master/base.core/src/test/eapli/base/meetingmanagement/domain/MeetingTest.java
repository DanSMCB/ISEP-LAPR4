package eapli.base.meetingmanagement.domain;

import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeetingTest {

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

    public MeetingTest() throws ParseException {
    }

    @Test
    void ensureMeetingMustHaveDuration() {
        System.out.println("must have meeting duration");

        assertThrows(IllegalArgumentException.class, () -> new Meeting(new MeetingTitle("meeting title"), newUser, null, new MeetingDate(date), new MeetingTime(time)));
    }

    @Test
    void ensureMeetingMustHaveDate() {
        System.out.println("must have exam date");

        assertThrows(IllegalArgumentException.class, () -> new Meeting(new MeetingTitle("meeting title"), newUser, new MeetingDuration(30), null, new MeetingTime(time)));
    }

    @Test
    void ensureMeetingMustHaveTime() {
        System.out.println("must have exam course");

        assertThrows(IllegalArgumentException.class, () -> new Meeting(new MeetingTitle("meeting title"), newUser, new MeetingDuration(30), new MeetingDate(date), null));
    }
}
