package eapli.base.Enrollment.Application;

import eapli.base.Enrollment.Domain.Enrollment;
import eapli.base.Enrollment.Repository.EnrollmentRepository;


public class ListEnrollmentsController {
    private final ListEnrollmentsService svc;

    public ListEnrollmentsController(final EnrollmentRepository enrollmentRepository) {

        svc = new ListEnrollmentsService(enrollmentRepository);
    }



    public Iterable<Enrollment> listEnrollments() {
        return svc.enrollments();
    }
}
