package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class ExamResult implements ValueObject {

    private float number;

    protected ExamResult(){
    }

    public ExamResult(float number) {
        this.number = number;
    }

    public static ExamResult valueOf(float number) {
        if (!isValidExamResult(number)) {
            throw new IllegalArgumentException("Invalid exam result format");
        }
        return new ExamResult(number);
    }

    private static boolean isValidExamResult(float number) {
        return number > 0 && number < 20;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResult that = (ExamResult) o;
        return Float.compare(that.number, number) == 0;
    }

    @Override
    public int hashCode() {
        return Float.hashCode(number);
    }
}