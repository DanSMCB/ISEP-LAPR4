package base.Classe.Domain;

import eapli.base.Classe.domain.*;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;

public class ClasseTest {


    final SystemUserBuilder userBuilder = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
    final SystemUser newUser = userBuilder
            .withUsername("systemUserTest")
            .withPassword("password")
            .withName("SystemUser", "Test")
            .withEmail("systemusertest@email.org")
            .withRoles(BaseRoles.STUDENT, BaseRoles.TEACHER)
            .build();

    private final Classe_Title title = new Classe_Title("Class Title");
    private final Classe_Start_Date start_date = new Classe_Start_Date(LocalDate.now());
    private final Classe_Finish_Date finish_date = new Classe_Finish_Date(LocalDate.now().plusDays(7));
    private final Classe_Start_Time start_time = new Classe_Start_Time(LocalTime.of(9, 0));
    private final Classe_Finish_Time finish_time = new Classe_Finish_Time(LocalTime.of(10, 30));
    private final eapli.base.Classe.domain.DayOfWeek day = new eapli.base.Classe.domain.DayOfWeek(1);

    private final Acronym acronym = new Acronym("XYZ");


    @Test
    void ensureClasseMustHaveStartDate() {
        assertThrows(IllegalArgumentException.class, () -> new Classe(title, null, finish_date, start_time, finish_time, day, acronym));
    }

    @Test
    void ensureClasseMustHaveFinishDate() {
        assertThrows(IllegalArgumentException.class, () -> new Classe(title, start_date, null, start_time, finish_time, day, acronym));
    }

    @Test
    void ensureClasseMustHaveStartTime() {
        assertThrows(IllegalArgumentException.class, () -> new Classe(title, start_date, finish_date, null, finish_time, day, acronym));
    }

    @Test
    void ensureClasseMustHaveFinishTime() {
        assertThrows(IllegalArgumentException.class, () -> new Classe(title, start_date, finish_date, start_time, null, day, acronym));
    }

    @Test
    void ensureClasseMustHaveTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Classe(null, start_date, finish_date, start_time, finish_time, day, acronym);
        });
    }
    @Test
    void ensureClasseMustHaveAcronym() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Classe(title, start_date, finish_date, start_time, finish_time, day, null);
        });
    }



}
