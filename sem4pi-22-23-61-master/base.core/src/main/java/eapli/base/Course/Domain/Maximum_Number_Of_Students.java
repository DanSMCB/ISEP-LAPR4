package eapli.base.Course.Domain;
import javax.persistence.*;

import eapli.framework.domain.model.ValueObject;
import lombok.*;

@Embeddable
public class Maximum_Number_Of_Students implements ValueObject {

    private int max_num;

    protected Maximum_Number_Of_Students(){}

    public Maximum_Number_Of_Students(int value){this.max_num=value;
    }

    public int getValue() {
        return max_num;
    }

    public void setValue(int value) {
        this.max_num = value;
    }
}
