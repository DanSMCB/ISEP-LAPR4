package eapli.base.Classe.aplication;


import java.time.LocalDate;
import java.time.LocalTime;

public class ClasseDTO {
    public String title;
    public LocalTime startTime;
    public LocalTime finishTime;
    public LocalDate startDate;
    public LocalDate finishDate;
    public int dayOfWeek;
    public String teacherAcronym;

    public ClasseDTO( String title, LocalTime startTime, LocalTime finishTime, LocalDate startDate, LocalDate finishDate, int dayOfWeek, String teacherAcronym) {
        this.title = title;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.dayOfWeek = dayOfWeek;
        this.teacherAcronym = teacherAcronym;
    }
}
