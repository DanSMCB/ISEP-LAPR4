package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.time.LocalTime;

@Embeddable
public class Classe_Start_Time implements ValueObject {

    @Column(nullable = false)
    private LocalTime start_time;


    protected Classe_Start_Time() {

    }

    public Classe_Start_Time(LocalTime start_time) {
        Preconditions.ensure(start_time != null, "Invalid start time");
        this.start_time = start_time;
    }

    public static Classe_Start_Time from(LocalTime start_time) {
        return new Classe_Start_Time(start_time);
    }

    public static Classe_Start_Time valueOf(LocalTime start_time) {
        return from(start_time);
    }

    @Override
    public String toString() {
        return String.format("Start Time: %s", start_time);
    }

    public LocalTime getStartTime() {
        return this.start_time;
    }
}
