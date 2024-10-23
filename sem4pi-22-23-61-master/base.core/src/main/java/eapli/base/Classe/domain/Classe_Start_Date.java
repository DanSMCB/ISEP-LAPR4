package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class Classe_Start_Date implements ValueObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate start_date;
    protected Classe_Start_Date() {
    }

    public Classe_Start_Date(LocalDate start_date) {
        if (start_date == null) {
            throw new IllegalArgumentException("Start date must not be null");
        }
        this.start_date = start_date;
    }

    public static Classe_Start_Date valueOf(LocalDate start_date) {
        return new Classe_Start_Date(start_date);
    }

    public LocalDate getStart_date() {
        return this.start_date;
    }

    @Override
    public String toString() {
        return start_date != null ? start_date.toString() : "";
    }
}
