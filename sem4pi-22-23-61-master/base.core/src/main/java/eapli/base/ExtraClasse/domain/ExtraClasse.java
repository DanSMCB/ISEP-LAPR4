package eapli.base.ExtraClasse.domain;

import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ExtraClasse  implements AggregateRoot<ExtraClasse_Title> {

    @EmbeddedId
    private ExtraClasse_Title title;
    private ExtraClasse_Start_Time start_time;
    private ExtraClasse_Finish_Time finish_time;
    private ExtraClasse_Day day;
    private Acronym acronym;


    public ExtraClasse(ExtraClasse_Title title, ExtraClasse_Start_Time start_time, ExtraClasse_Finish_Time finish_time, ExtraClasse_Day day, Acronym acronym) {
        Preconditions.nonNull(title, "Title should not be null");
        this.title = title;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.day = day;
        this.acronym = acronym;
    }

    public ExtraClasse() {
    }


    public ExtraClasse_Title getTitle() {
        return this.title;
    }

    @Override
    public ExtraClasse_Title identity() {
        return this.title;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ExtraClasse)) {
            return false;
        }
        return this.title.equals(((ExtraClasse) other).getTitle());
    }

    @Override
    public int compareTo(ExtraClasse_Title other) {
        return this.title.compareTo(other);
    }

    public void setTitle(ExtraClasse_Title title) {
        this.title = title;
    }

    public ExtraClasse_Start_Time getStart_time() {
        return start_time;
    }

    public void setStart_time(ExtraClasse_Start_Time start_time) {
        this.start_time = start_time;
    }

    public ExtraClasse_Finish_Time getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(ExtraClasse_Finish_Time finish_time) {
        this.finish_time = finish_time;
    }

    public ExtraClasse_Day getDay() {
        return day;
    }

    public void setDay(ExtraClasse_Day day) {
        this.day = day;
    }

    public Acronym getAcronym() {
        return acronym;
    }

    public void setAcronym(Acronym acronym) {
        this.acronym = acronym;
    }
}
