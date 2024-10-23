package eapli.base.Course.Domain;
import javax.persistence.*;

import eapli.framework.domain.model.ValueObject;
import lombok.*;


@Embeddable
public class Small_Textual_Description implements ValueObject {
    
    private String desc;

    public Small_Textual_Description(String value){
        this.desc=value;
    }

    public Small_Textual_Description() {

    }

    public String getValue() {
        return desc;
    }

    public void setValue(String value) {
        this.desc = value;
    }
}
