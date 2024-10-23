package eapli.base.meetingmanagement.domain;

import eapli.base.exammanagement.domain.ExamTitle;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MeetingTitle implements ValueObject {

    private String title;

    protected MeetingTitle() {
    }

    public MeetingTitle(String title) throws IllegalArgumentException {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeetingTitle other = (MeetingTitle) o;
        return Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
