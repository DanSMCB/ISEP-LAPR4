package eapli.base.app.backoffice.console.presentation.course;

import eapli.base.Course.Domain.Course;
import eapli.framework.visitor.Visitor;

public class CoursePrinter implements Visitor<Course> {

    @Override
    public void visit(final Course visitee) {
        System.out.printf("%-10s%-30s%-4s", visitee.identity(), visitee.getSmallTextualDescription(), visitee.getTeacherInCharge().toString());
    }
}