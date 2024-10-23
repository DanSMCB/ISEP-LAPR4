package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class ExamDate implements ValueObject {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date examDate;

    protected ExamDate() {
    }

    public ExamDate(Date examDate) {
        if (!isValidExamDate(examDate)) {
            throw new IllegalArgumentException("Invalid exam date format");
        }
        this.examDate = examDate;
    }

    private static boolean isValidExamDate(Date examDate) {
        Date date = new Date(System.currentTimeMillis());
        return examDate.after(date);
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamDate that = (ExamDate) o;
        return examDate.equals(that.examDate);
    }

    @Override
    public String toString() {
        return examDate.toString();
    }
}