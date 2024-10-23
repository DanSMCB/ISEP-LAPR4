package eapli.base.SharedBoard.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class NumberofRows implements ValueObject {

    private int num_row;

    public NumberofRows(int value){
        this.num_row=value;
    }

    protected NumberofRows(){}

    public static NumberofRows from(int numberOfRows) throws IllegalArgumentException {
        Preconditions.ensure(numberOfRows > 0, "Number of rows must be greater than zero");
        return new NumberofRows(numberOfRows);
    }

    public static NumberofRows valueOf(int numberOfRows) throws IllegalArgumentException {
        return from(numberOfRows);
    }

    public int getValue(){
        return num_row;
    }

    @Override
    public String toString() {
        return "Number of Rows: " + num_row;
    }

}
