package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class DayOfWeek implements ValueObject {

    private int day;

    protected DayOfWeek() {
    }

    public DayOfWeek(int day) throws IllegalArgumentException {
        Preconditions.ensure(day >= 1 && day <= 7, "Invalid day of week");
        this.day = day;
    }

    public static DayOfWeek valueOf(int day) throws IllegalArgumentException {
        return new DayOfWeek(day);
    }

    @Override
    public String toString() {
        return String.format("Day of Week: %d", day);
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        Preconditions.ensure(day >= 1 && day <= 7, "Invalid day of week");
        this.day = day;
    }
}
