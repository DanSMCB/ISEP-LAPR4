package eapli.base.ExtraClasse.domain;

import eapli.base.Classe.domain.Classe_Title;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ExtraClasse_Title implements ValueObject, Comparable<ExtraClasse_Title> {

    private String title;

    protected ExtraClasse_Title() { } // needed for JPA

    private ExtraClasse_Title(String title){
        this.title = title;
    }

    public static ExtraClasse_Title from(String title){
        Preconditions.ensure(!(title == null || title.isEmpty()), "Title invalido");
        return new ExtraClasse_Title(title);
    }

    public static ExtraClasse_Title valueOf(String title){
        return from(title);
    }

    @Override
    public String toString() {
        return String.format("Title : %s", title);
    }

    @Override
    public int compareTo(ExtraClasse_Title o) {
        return this.title.compareTo(o.title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classe_Title other = (Classe_Title) o;
        return Objects.equals(title, other.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
