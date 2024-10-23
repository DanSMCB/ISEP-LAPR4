package eapli.base.Enrollment.Application;

import eapli.base.Enrollment.Domain.Enrollment;
import eapli.base.Enrollment.Repository.EnrollmentRepository;

public class ApproveEnrollmentController {

    private final EnrollmentRepository enrollmentRepository;

    public ApproveEnrollmentController(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void approveEnrollment(Enrollment enrollment, int isToBeAccepted) {
        if (isToBeAccepted == 1) {
            enrollment.acceptEnrollment();
        } else {
            enrollment.rejectEnrollment();
        }
        enrollmentRepository.save(enrollment);
    }


}
