package eapli.base.SharedBoard.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class NumberofColumns implements ValueObject {
    private int num_col;

    public NumberofColumns(int value){
        this.num_col=value;
    }

    protected NumberofColumns(){}

    public static NumberofColumns from(int value) throws IllegalArgumentException {
        Preconditions.ensure(value > 0, "Number of columns must be greater than 0");
        return new NumberofColumns(value);
    }

    public static NumberofColumns valueOf(int value) throws IllegalArgumentException {
        return from(value);
    }

    public int getValue(){
        return num_col;
    }

    @Override
    public String toString() {
        return String.format("Number of Columns: %d", num_col);
    }
}
