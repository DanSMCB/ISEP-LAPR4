package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Classe_Finish_Date implements ValueObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate finish_date;

    // No argument constructor, used by JPA/Hibernate
    protected Classe_Finish_Date() {}

    public Classe_Finish_Date(LocalDate finish_date) {
        if (finish_date == null) {
            throw new IllegalArgumentException("Finish date cannot be null");
        }
        this.finish_date = finish_date;
    }

    public static Classe_Finish_Date valueOf(LocalDate finish_date) {
        return new Classe_Finish_Date(finish_date);
    }

    @Override
    public String toString() {
        return finish_date.toString();
    }

    public LocalDate getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(LocalDate finish_date) {
        if (finish_date == null) {
            throw new IllegalArgumentException("Finish date cannot be null");
        }
        this.finish_date = finish_date;
    }
}
