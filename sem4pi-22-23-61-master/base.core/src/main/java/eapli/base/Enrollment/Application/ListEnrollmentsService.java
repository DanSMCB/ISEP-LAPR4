package eapli.base.Enrollment.Application;

import eapli.base.Enrollment.Domain.Enrollment;
import eapli.base.Enrollment.Repository.EnrollmentRepository;

public class ListEnrollmentsService {

    private final EnrollmentRepository enrollmentRepository;

    public ListEnrollmentsService(final EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Iterable<Enrollment> enrollments() {
        return enrollmentRepository.findAll();
    }
}
