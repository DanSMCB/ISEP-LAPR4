package eapli.base.Course.Domain;

import javax.persistence.*;
import lombok.*;

@Embeddable
@EqualsAndHashCode
public class Course_ID implements Comparable<Course_ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected Course_ID(){}

    public Course_ID(Long id){
        this.id=id;
    }
    @Override
    public int compareTo(Course_ID o) {
        return 0;
    }
}
