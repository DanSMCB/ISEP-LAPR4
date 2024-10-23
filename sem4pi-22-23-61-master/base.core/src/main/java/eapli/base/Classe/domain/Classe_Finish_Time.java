package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class Classe_Finish_Time implements ValueObject {
    private LocalTime finish_time;

    protected Classe_Finish_Time() {
    }

    public Classe_Finish_Time(LocalTime finish_time) throws IllegalArgumentException{
        Preconditions.ensure(finish_time != null, "Invalid finish time");
        this.finish_time = finish_time;
    }

    public static Classe_Finish_Time from(LocalTime finish_time) throws IllegalArgumentException{
        return new Classe_Finish_Time(finish_time);
    }

    public static Classe_Finish_Time valueOf(LocalTime finish_time) throws IllegalArgumentException{
        return from(finish_time);
    }

    @Override
    public String toString() {
        return String.format("Finish Time: %s", finish_time);
    }

    public LocalTime getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(LocalTime finish_time) {
        Preconditions.ensure(finish_time != null, "Invalid finish time");
        this.finish_time = finish_time;
    }
}
