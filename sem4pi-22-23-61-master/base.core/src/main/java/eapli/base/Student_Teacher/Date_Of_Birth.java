package eapli.base.Student_Teacher;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Date_Of_Birth
{
    private LocalDate dateOfBirth;

    public Date_Of_Birth(LocalDate dateOfBirth) {
        this.dateOfBirth=dateOfBirth;
    }

    public static Date_Of_Birth valueOf(LocalDate dateOfBirth) {
        return new Date_Of_Birth(dateOfBirth);
    }

    @Override
    public String toString() {
        return dateOfBirth.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date_Of_Birth)) return false;
        Date_Of_Birth that = (Date_Of_Birth) o;
        return dateOfBirth.equals(that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
