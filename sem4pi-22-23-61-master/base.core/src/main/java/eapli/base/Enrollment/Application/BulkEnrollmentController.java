package eapli.base.Enrollment.Application;

import eapli.base.Enrollment.Domain.BulkEnrollment.BulkEnrollment;
import eapli.base.Enrollment.Repository.EnrollmentRepository;

public class BulkEnrollmentController {
    private BulkEnrollment bulkEnrollment;
    private EnrollmentRepository enrollmentRepository;

    public BulkEnrollmentController(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository=enrollmentRepository;
    }

    public void bulkEnrollment(String csvFilePath) {
        bulkEnrollment.bulkEnrollmentReadFile(csvFilePath);
        //save is given in bulkEnrollmentReadFile
    }
}
