package eapli.base.SharedBoard.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Shared_Board_Title implements ValueObject, Comparable<Shared_Board_Title> {

    private String title;

    protected Shared_Board_Title() {
    }

    public Shared_Board_Title(String title) throws IllegalArgumentException {
        Preconditions.ensure(title != null && !title.isEmpty(), "Invalid title");
        this.title = title;
    }

    public static Shared_Board_Title valueOf(String title) throws IllegalArgumentException {
        return new Shared_Board_Title(title);
    }

    @Override
    public String toString() {
        return String.format("Title: %s", title);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        Preconditions.ensure(title != null && !title.isEmpty(), "Invalid title");
        this.title = title;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shared_Board_Title other = (Shared_Board_Title) o;
        return Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }


    @Override
    public int compareTo(Shared_Board_Title o) {
        return this.title.compareTo(o.title);
    }
}
