package eapli.base.Classe.domain;

import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Classe implements AggregateRoot<Classe_Title> {

    @Column(unique = true)
    @EmbeddedId
    private Classe_Title title;

    private Classe_Start_Date start_date;
    private Classe_Finish_Date finish_date;
    private Classe_Start_Time start_time;
    private Classe_Finish_Time finish_time;
    private DayOfWeek day;
    private Acronym acronym;


    protected Classe() {
    }

    public Classe(Classe_Title title, Classe_Start_Date start_date, Classe_Finish_Date finish_date, Classe_Start_Time start_time, Classe_Finish_Time finish_time, DayOfWeek day, Acronym acronym) {
        Preconditions.nonNull(title, "Title should not be null");
        this.title = title;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day = day;
        this.acronym = acronym;
    }

    public void changeTitle(Classe_Title newTitle) {
        Preconditions.nonNull(newTitle, "Title should not be null");
        this.title = newTitle;
    }

    public void changeStartTime(Classe_Start_Time newStartTime) {
        Preconditions.nonNull(newStartTime, "Start time should not be null");
        this.start_time = newStartTime;
    }

    public void changeFinishTime(Classe_Finish_Time newFinishTime) {
        Preconditions.nonNull(newFinishTime, "Finish time should not be null");
        this.finish_time = newFinishTime;
    }

    @Override
    public Classe_Title identity() {
        return this.title;
    }

    @Override
    public boolean sameAs(Object other) {
        return other instanceof Classe && this.title.equals(((Classe)other).title);
    }

    public Classe_Title getTitle() {
        return title;
    }

    public Classe_Start_Date getStartDate() {
        return start_date;
    }

    public Classe_Finish_Date getFinishDate() {
        return finish_date;
    }

    public Classe_Start_Time getStartTime() {
        return start_time;
    }

    public Classe_Finish_Time getFinishTime() {
        return finish_time;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public Acronym getAcronym() {
        return acronym;
    }


    public void setTitle(Classe_Title title) {
        this.title = title;
    }

    public void setStart_date(Classe_Start_Date start_date) {
        this.start_date = start_date;
    }

    public void setFinish_date(Classe_Finish_Date finish_date) {
        this.finish_date = finish_date;
    }

    public void setStart_time(Classe_Start_Time start_time) {
        this.start_time = start_time;
    }

    public void setFinish_time(Classe_Finish_Time finish_time) {
        this.finish_time = finish_time;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setAcronym(Acronym acronym) {
        this.acronym = acronym;
    }
}
