package eapli.base.ExtraClasse.aplication;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExtraClasseDTO {
    public String title;
    public LocalTime startTime;
    public LocalTime finishTime;
    public int dayOfWeek;
    public String teacherAcronym;

    public ExtraClasseDTO( String title, LocalTime startTime, LocalTime finishTime, int dayOfWeek, String teacherAcronym) {
        this.title = title;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.dayOfWeek = dayOfWeek;
        this.teacherAcronym = teacherAcronym;
    }
}
