package eapli.base.Student_Teacher.Teacher.Domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Acronym {
    private String value;

    protected Acronym() {
    }

    public Acronym(String value) {
        this.value = value.toUpperCase();
    }

    public static Acronym valueOf(String value) {
        return new Acronym(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acronym acronym = (Acronym) o;
        return value.equals(acronym.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value.toUpperCase();
    }


}
