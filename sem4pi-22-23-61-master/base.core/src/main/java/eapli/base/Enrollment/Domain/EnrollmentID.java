package eapli.base.Enrollment.Domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EnrollmentID implements Comparable<EnrollmentID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Override
    public int compareTo(EnrollmentID o) {
        return 0;
    }
}
