package eapli.base.Course.Domain;
import javax.persistence.*;

import eapli.framework.domain.model.ValueObject;
import lombok.*;


@Embeddable
public class Minimum_Number_Of_Students implements ValueObject {

    private int min_num;

    public Minimum_Number_Of_Students(){
    }
    public Minimum_Number_Of_Students(int value){
        this.min_num=value;
    }

    public int getValue() {
        return min_num;
    }

    public void setValue(int value) {
        this.min_num = value;
    }
}