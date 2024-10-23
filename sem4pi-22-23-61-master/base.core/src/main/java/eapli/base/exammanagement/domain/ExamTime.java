package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class ExamTime implements ValueObject {

    @DateTimeFormat(pattern = "hh:mm")
    private Date openDate;
    @DateTimeFormat(pattern = "hh:mm")
    private Date closeDate;

    protected ExamTime() {
    }

    public ExamTime(Date openDate, Date closeDate) {
        if (!isValidExamTime(openDate, closeDate)) {
            throw new IllegalArgumentException("Invalid exam time format");
        }
        this.openDate = openDate;
        this.closeDate = closeDate;
    }

    private static boolean isValidExamTime(Date openDate, Date closeDate) {
        return closeDate.after(openDate);
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return openDate.toString() + " - " + closeDate.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamTime examTime = (ExamTime) o;
        return openDate.equals(examTime.openDate) && closeDate.equals(examTime.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openDate, closeDate);
    }
}