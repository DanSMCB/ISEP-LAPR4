package eapli.base.Enrollment.Domain;

import javax.persistence.Embeddable;

@Embeddable
public class EnrollmentDescription {
    private String value;

    public EnrollmentDescription(String value) {
        this.value = value;
    }

    public EnrollmentDescription() {
    }
}

