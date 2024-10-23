package eapli.base.app.backoffice.console.presentation.enrollment;

import eapli.base.Enrollment.Domain.Enrollment;
import eapli.framework.visitor.Visitor;

public class EnrollmentPrinter implements Visitor<Enrollment> {

        @Override
        public void visit(final Enrollment visitee) {
            System.out.printf("%-10s%-30s%-4s", visitee.identity(),visitee.getStudent().toString(), visitee.getCourse().toString(),visitee.getDescription());
        }
    }

