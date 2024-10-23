package eapli.base.ExtraClasse.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class ExtraClasse_Finish_Time implements ValueObject {
    private LocalTime finish_time;


    protected ExtraClasse_Finish_Time() {}

    
    public ExtraClasse_Finish_Time(LocalTime finish_time) {
        Preconditions.ensure(finish_time != null, "Invalid finish time");
        this.finish_time = finish_time;
    }

    public static ExtraClasse_Finish_Time from(LocalTime finish_time) throws IllegalArgumentException{
        return new ExtraClasse_Finish_Time(finish_time);
    }

    public static ExtraClasse_Finish_Time valueOf(LocalTime finish_time) {
        return new ExtraClasse_Finish_Time(finish_time);
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
