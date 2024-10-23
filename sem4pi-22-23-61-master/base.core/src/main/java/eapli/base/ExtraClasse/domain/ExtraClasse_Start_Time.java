package eapli.base.ExtraClasse.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class ExtraClasse_Start_Time implements ValueObject {
    private LocalTime start_time;


    protected ExtraClasse_Start_Time() {}


    public ExtraClasse_Start_Time(LocalTime start_time) {
        Preconditions.ensure(start_time != null, "Invalid start time");
        this.start_time = start_time;
    }

    public static ExtraClasse_Start_Time from(LocalTime start_time) throws IllegalArgumentException {
        return new ExtraClasse_Start_Time(start_time);
    }

    public static ExtraClasse_Start_Time valueOf(LocalTime start_time) {
        return new ExtraClasse_Start_Time(start_time);
    }

    @Override
    public String toString() {
        return String.format("Start Time: %s", start_time);
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        Preconditions.ensure(start_time != null, "Invalid start time");
        this.start_time = start_time;
    }
}
