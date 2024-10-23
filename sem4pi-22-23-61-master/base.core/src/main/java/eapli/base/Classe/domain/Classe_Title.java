package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.util.Objects;

@Embeddable
public class Classe_Title implements ValueObject, Comparable<Classe_Title> {

    @Column(nullable = false)
    private  String title;


    protected Classe_Title() {
    }

    public Classe_Title(String title) {
        Preconditions.ensure(title != null && !title.trim().isEmpty(), "Title invalido");
        this.title = title;
    }

    public static Classe_Title from(String title) {
        return new Classe_Title(title);
    }

    public static Classe_Title valueOf(String title) {
        return from(title);
    }

    @Override
    public String toString() {
        return String.format("Title : %s", title);
    }

    public String getTitle() {
        return this.title;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe_Title other = (Classe_Title) o;
        return Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }


    @Override
    public int compareTo(Classe_Title o) {
        return this.title.compareTo(o.title);
    }

}
