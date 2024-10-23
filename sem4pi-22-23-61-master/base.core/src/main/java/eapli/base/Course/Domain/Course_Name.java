package eapli.base.Course.Domain;
import javax.persistence.*;

import eapli.framework.domain.model.ValueObject;
import lombok.*;

@Embeddable
public class Course_Name implements ValueObject, Comparable<Course_Name>{
    private String value;

    public Course_Name() {

    }

    public String getValue() {
        return value;
    }

    public Course_Name(String value){
        this.value=value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Course_Name o) {
        return 0;
    }
}
