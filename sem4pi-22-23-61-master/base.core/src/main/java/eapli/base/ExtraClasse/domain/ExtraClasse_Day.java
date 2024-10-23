package eapli.base.ExtraClasse.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;

@Embeddable
public class ExtraClasse_Day implements ValueObject {

    private int day;

    protected ExtraClasse_Day() {

    }

    public ExtraClasse_Day(int day) {
        Preconditions.ensure(day >= 1 && day <= 7, "Invalid day of week");
        this.day = day;
    }

    public static ExtraClasse_Day from(int day) throws IllegalArgumentException {
        return new ExtraClasse_Day(day);
    }

    public static ExtraClasse_Day valueOf(int day) throws IllegalArgumentException {
        return from(day);
    }



    @Override
    public String toString() {
        return String.format("Day of Week: %d", day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
