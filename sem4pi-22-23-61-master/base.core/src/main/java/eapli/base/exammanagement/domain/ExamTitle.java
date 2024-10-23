package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ExamTitle implements ValueObject {

    private String title;

    protected ExamTitle() {
    }

    public ExamTitle(String title) throws IllegalArgumentException {
        Preconditions.ensure(title != null && !title.isEmpty(), "Invalid title");
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Title: %s", title);
    }

    public String getTitle() {
        return this.title;
    }

    public String setTitle() {
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamTitle other = (ExamTitle) o;
        return Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
